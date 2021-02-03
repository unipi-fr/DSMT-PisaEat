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


% TODO: STUDIARE E IMPLEMENTARE COME LEGGERE E RITORNARE UN JSON CON LA LISTA DEI MESSAGGI
get_messages_json(Req, State) ->
    SessionId = cowboy_req:binding(sessionId, Req),
    io:format("get_messages_json Chiamata con session id = ~w\n", SessionId),
    Body = "{\"username\": \"tizio\", \"message\": \"Daje\"}",
    {Body, Req, State}.

% TODO: STUDIARE E IMPLEMENTARE COME RECUPERARE UN JSON DALLA RICHIESTA,
% SALVARLO NEL DB E RITORNARE UN MESSAGGIO OK
post_message_json(Req, State) ->
    SessionId = cowboy_req:binding(sessionId, Req),
    io:format("ritorna_post Chiamata con session id = ~w\n", SessionId),
    Body = "{\"username\": \"tizio\", \"message\": \"Daje\"}",
    {Body, Req, State}.

