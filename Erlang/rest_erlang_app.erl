-module(rest_erlang_app).
-behaviour(application).

-export([start/2]).
-export([stop/1]).

start(_Type, _Args) ->
    Dispatch = cowboy_router:compile([
		%% {HostMatch, list({PathMatch, Handler, InitialState})}
        {'_', [
				{"/", rest_handler, [help]},
				{"/help", rest_handler, [help]},
				{"/api/chat/:session_id", rest_handler, [chat]},
        {"/api/chat/write/:session_id", rest_handler, [message]}
			  ]}
    ]),
    {ok, _} = cowboy:start_clear(my_http_listener,
        [{port, 8080}],
        #{env => #{dispatch => Dispatch}}
    ),
    rest_erlang_sup:start_link().

stop(_State) ->
	ok.
