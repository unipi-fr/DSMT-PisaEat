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
        post_message_json/2
	]).

% in rest_erlang_app lo stato del server è specificato con una lista contenente solo un atom es. [help] oppure [chat],
% questo record serve ad essere sicuri del tipo di dato usato come stato del server. Guarda la init/2 per vedere come è usato.
-record(state, {op}).


% chiamata che recupera lo stato, e fa scattare la catena di callback rest di cowboy
init(Req, Opts) ->
    [Op | _] = Opts,
    State = #state{op=Op},
    {cowboy_rest, Req, State}.


% chiamata in automatico da cowboy per controllare ad ogni richiesta in arrivo se il metodo è supportato
allowed_methods(Req, State) ->
    Methods = [<<"GET">>, <<"POST">>],
    {Methods, Req, State}.


% CHIAMATA SOLO IN CASO DI GET, specifica il tipo di dato offerto
content_types_provided(Req, State) ->
    {[
        {<<"application/json">>, handle_get_json}
     ], Req, State}.


% CHIAMATA SOLO IN CASO DI POST, specifica il tipo di dato accettato
content_types_accepted(Req, State) ->
    {[
        {<<"application/json">>, handle_post_json}
     ], Req, State}.


handle_get_json(Req, #state{op=Op} = State) ->
    {Body_resp, Req_resp, State_resp} = case Op of
        help ->
            get_help_json(Req, State);
        chat ->
			get_messages_json(Req, State)
    end,
    {Body_resp, Req_resp, State_resp}.


handle_post_json(Req, #state{op=Op} = State) ->
    {Body_resp, Req_resp, State_resp} = case Op of
        chat ->
            post_message_json(Req, State)
    end,
    {Body_resp, Req_resp, State_resp}.


% ritorna messaggio per sapere cosa sia possibile fare
get_help_json(Req, State) ->
    Body = "{
        \"GET /\": \"retrieve help json file\",
        \"GET /api/help\": \"retrieve help json file\",
        \"GET /api/chat/ID\": \"retrieve a json file with a list of all messages sent in the session ID\",
        \"POST /api/chat/ID\": \"send a message in the form of a json file to be saved in the session ID; returns the json saved\"
    }",
    {Body, Req, State}.


% ritorna lista di messaggi appartenenti alla sessione passata nell'url
get_messages_json(Req, State) ->
    % recupero il session id
    SessionIdBin = cowboy_req:binding(session_id, Req),
    SessionId = binary_to_list(SessionIdBin),
    % recupero path file dets dove trovo i messaggi
    {ok, Recordfilename} = application:get_env(rest_erlang, records_file_name),
    dets:open_file(records_db, [{file, Recordfilename}, {type, bag}]),

    Items = dets:lookup(records_db, SessionId),
    dets:close(records_db),  
    F = fun (Item) ->
                {Id, Data} = Item,
                LenMessage = length(Data),
                Message = lists:sublist(Data, 2, LenMessage - 2),
                Result = io_lib:format("{\"bookSessionId\": \"~s\", ~s}",
                          [Id, Message]),
            Result
        end,
    ItemsMapped = lists:map(F, Items),
    Itemsjoined = lists:join(",\n", ItemsMapped),
    Body = "
        {
        \"list\": [~s]
        }",
    Body1 = io_lib:format(Body, [Itemsjoined]),  
    {Body1, Req, State}.


% aggiunge il tempo con rappresentazione UTC
append_time_to_json(OriginalJson) ->

    OriginalLen = length(OriginalJson),
    Substr = lists:sublist(OriginalJson,OriginalLen - 1),

    {{Year, Month, Day},{Hour, Minute, Seconds}} = calendar:universal_time(),

    Body = ",\"datetimeUTC\": \"~4..0B-~2..0B-~2..0BT~2..0B:~2..0B:~2..0BZ\"}",
    Body1 = io_lib:format(Body, [ Year, Month, Day, Hour, Minute, Seconds]),
    Body_resp = Substr ++ Body1,

    Body_resp.


% salva messaggio in messages.dets e ritorna il json completo al chiamante
post_message_json(Req, State) ->
    SessionIdBin = cowboy_req:binding(session_id, Req),
    SessionId = binary_to_list(SessionIdBin),

    {ok, Json, Req1} = cowboy_req:read_body(Req),
    JsonList = binary_to_list(Json),
    SavedJson = append_time_to_json(JsonList),
    {ok, Recordfilename} = application:get_env(rest_erlang, records_file_name),
    {ok, _} = dets:open_file(records_db, [{file, Recordfilename}, {type, bag}]),
    ok = dets:insert(records_db, {SessionId, SavedJson}),
    ok = dets:sync(records_db),
    ok = dets:close(records_db),
    CompleteJson = append_session_id_to_json(SavedJson, SessionId),

    Req_resp = cowboy_req:reply(200,
        #{<<"content-type">> => <<"application/json">>},
    CompleteJson,
     Req1),
   
    {true, Req_resp, State}.


% serve per ritornare il json completo
append_session_id_to_json(OriginalJson, SessionId) ->

    OriginalLen = length(OriginalJson),
    Substr = lists:sublist(OriginalJson,OriginalLen - 1),

    Body = ",\"bookSessionId\": \"~s\"}",
    Body1 = io_lib:format(Body, [SessionId]),
    Body_resp = Substr ++ Body1,

    Body_resp.

