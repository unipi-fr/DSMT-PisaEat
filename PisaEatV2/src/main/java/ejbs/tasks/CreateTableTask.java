package ejbs.tasks;

import database.ITableDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import entities.Table;

import java.util.concurrent.Callable;

public class CreateTableTask implements Callable<Table> {
    private final ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());

    private Table table;

    public CreateTableTask(Table table) {
        this.table = table;
    }

    @Override
    public Table call() throws Exception {
        return iTableDao.createTable(table);
    }
}
