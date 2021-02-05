-module(rest_handler).
-behavior(cowboy_handler).

-export([init/2,
		allowed_methods/2,
		content_types_provided/2,
		content_types_accepted/2,
		get_help_json/2,
        handle_get_json/2,
        get_messages_json/2,
        handle_post_json/2,
        post_message_json/2,
        generate_id/0,
        append_time_to_json/0
	]).

% in rest_erlang_app lo stato del server è specificato con una lista contenente solo un atom es. [help] oppure [chat],
% questo record serve ad essere sicuri del tipo di dato usato come stato del server. Guarda la init/2 per vedere come è usato.
-record(state, {op}).


% chiamata che recupera lo stato, e fa scattare la catena di callback rest di cowboy
init(Req, Opts) ->
    [Op | _] = Opts,
    State = #state{op=Op},
    io:format("Init Chiamata\n"),
    {cowboy_rest, Req, State}.


% chiamata in automatico da cowboy per controllare ad ogni richiesta in arrivo se il metodo è supportato
allowed_methods(Req, State) ->
    Methods = [<<"GET">>, <<"POST">>],
    io:format("allowed_methods Chiamata\n"),
    {Methods, Req, State}.


% CHIAMATA SOLO IN CASO DI GET, specifica il tipo di dato offerto
content_types_provided(Req, State) ->
    io:format("content_types_provided Chiamata\n"),
    {[
        {<<"application/json">>, handle_get_json}
     ], Req, State}.


% CHIAMATA SOLO IN CASO DI POST, specifica il tipo di dato accettato
content_types_accepted(Req, State) ->
    io:format("content_types_accepted Chiamata\n"),
    {[
        {<<"application/json">>, handle_post_json}
     ], Req, State}.


handle_get_json(Req, #state{op=Op} = State) ->
    io:format("handle_get_json Chiamata con operazione ~w\n", [Op]),
    {Body_resp, Req_resp, State_resp} = case Op of
        help ->
            get_help_json(Req, State);
        chat ->
			get_messages_json(Req, State)
    end,
    {Body_resp, Req_resp, State_resp}.


handle_post_json(Req, #state{op=Op} = State) ->
    io:format("handle_post_json Chiamata con operazione ~w\n", [Op]),
    {Body_resp, Req_resp, State_resp} = case Op of
        chat ->
            post_message_json(Req, State)
    end,
    {Body_resp, Req_resp, State_resp}.


% TODO: AGGIUSTARE I PERCORSI STAMPATI E RIPULIRE IL MESSAGGIO
% ritorna messaggio per sapere cosa sia possibile fare
get_help_json(Req, State) ->
    io:format("get_help_json Chiamata\n"),
    Body = "{
    \"GET /help\": \"retrieve help json file\",
    \"GET /api/chat/ID\": \"retrieve a json file with a list of all messages sent in the session ID\",
    \"POST /api/chat/ID\": \"send a message in the form of a json file to be saved in the session ID\"
}",
    {Body, Req, State}.



get_messages_json(Req, State) ->
    RecordId = cowboy_req:binding(session_id, Req),
    RecordId1 = binary_to_list(RecordId),

    {ok, Recordfilename} = application:get_env(rest_erlang, records_file_name),
io:format("Fino qui tutto bene 1\n"),   
    dets:open_file(records_db, [{file, Recordfilename}, {type, bag}]),
   io:format("Fino qui tutto bene 2\n"),    
    Items = dets:lookup(records_db, RecordId1),
io:format("Fino qui tutto bene 3\n"),   
    dets:close(records_db),
io:format("Fino qui tutto bene 4\n"),   
   % Items1 = lists:sort(Items),
io:format("Fino qui tutto bene 5\n"),   
    %Items2 = lists:flatten(lists:join(",\n", Items)),
    %Items1 = binary_to_list(Items),
    Body = "
{
    \"list\": ~p
}",
io:format("Fino qui tutto bene 6\n"),
    Body1 = io_lib:format(Body, [Items]),
    io:format("Fino qui tutto bene 7\n"),   
    {Body1, Req, State}.


% TODO: STUDIARE E IMPLEMENTARE COME RECUPERARE UN JSON DALLA RICHIESTA,
% SALVARLO NEL DB E RITORNARE UN MESSAGGIO OK
post_message_json(Req, State) ->
    SessionId = cowboy_req:binding(session_id, Req),
    SessionIdList = binary_to_list(SessionId),
    io:format("post_message_json Chiamata con session id = ~p\n", [SessionIdList]),
    %id = generate_id(),
    %io:format("La generate id non è il problema\n"),
    %io:format("Id del nuovo messaggio = ~p\n", [id]),
    {ok, Json, Req1} = cowboy_req:read_body(Req),
    io:format("Corpo json passato ~p\n", [Json]),
    JsonList = binary_to_list(Json),
    %Json_concatenato = append_time_to_json(Json),
    %io:format("Data recuperata dalla richiesta ~p\n", [Json_concatenato]),
    {ok, Recordfilename} = application:get_env(rest_erlang, records_file_name),
    {ok, _} = dets:open_file(records_db, [{file, Recordfilename}, {type, bag}]),
    ok = dets:insert(records_db, {SessionIdList, JsonList}),
    ok = dets:sync(records_db),
    ok = dets:close(records_db),
    Req_resp = cowboy_req:reply(204, Req1),
    {true, Req_resp, State}.


% da debuggare
generate_id() ->
    io:format("Chiamata generate_id()\n"),
    {ok, Statefilename} = application:get_env(rest_erlang, state_file_name),
    io:format("Fino qui tutto bene 1\n"),
    dets:open_file(record_state_tab, [{file, Statefilename}, {type, bag}]),
    io:format("Fino qui tutto bene 2\n"),
    Records = dets:lookup(record_state_tab, current_id),
    io:format("Fino qui tutto bene 3\n"),
    Response = case Records of
        [{current_id, CurrentId}] ->
            NextId = CurrentId + 1,
            io:format("Fino qui tutto bene 4\n"),
            dets:insert(record_state_tab, {current_id, NextId}),
            io:format("Fino qui tutto bene 5\n"),
            Id = lists:flatten(io_lib:format("id_~4..0B", [CurrentId])),
            io:format("Fino qui tutto bene 6\n"),
            Id;
        [] ->
            error
    end,
    dets:close(record_state_tab),
    io:format("Fino qui tutto bene 7\n"),
    Response.


append_time_to_json() ->
    {Hour, Minute, Second} = erlang:time(),
    {Year, Month, Day} = erlang:date(),
    Body = "
{
    \"time\": \"~2..0B:~2..0B:~2..0B\",
    \"date\": \"~4..0B/~2..0B/~2..0B\"
}",
    Body1 = io_lib:format(Body, [
        Hour, Minute, Second,
        Year, Month, Day
    ]),
    Body2 = list_to_binary(Body1),
    {Body2}.

