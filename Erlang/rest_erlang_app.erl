-module(rest_erlang_app).
-behaviour(application).

-export([start/2]).
-export([stop/1]).

start(_Type, _Args) ->
    Dispatch = cowboy_router:compile([
		%% {HostMatch, list({PathMatch, Handler, InitialState})}
        {'_', [
				{"/", rest_handler, [help]},
				{"/api/help", rest_handler, [help]},
				{"/api/chat/:session_id", rest_handler, [chat]}
			  ]}
    ]),
    {ok, _} = cowboy:start_clear(my_http_listener,
        [{port, 8081}],
        #{env => #{dispatch => Dispatch}}
    ),
    error_logger:tty(false),
    rest_erlang_sup:start_link().

stop(_State) ->
	ok.
