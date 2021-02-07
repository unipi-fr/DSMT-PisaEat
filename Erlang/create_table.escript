#!/usr/bin/env escript    

main([Filename]) ->
    Filename1 = io_lib:format("~smessages.dets", [Filename]),
    dets:open_file(record_tab, [{file, Filename1}, {type, bag}]),
    dets:close(record_tab);
main(["--help"]) ->
    usage();
main([]) ->
    usage().

usage() ->
    io:fwrite("usage: create_table.escript <db-file-stem>~n", []).