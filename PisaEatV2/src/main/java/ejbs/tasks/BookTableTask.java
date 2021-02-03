package ejbs.tasks;

import entities.Table;

import java.util.concurrent.Callable;

public class BookTableTask implements Callable<Table> {
    @Override
    public Table call() throws Exception {
        return null;
    }
}
