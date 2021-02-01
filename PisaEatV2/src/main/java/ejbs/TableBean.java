package ejbs;

import database.ITableDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import entities.Table;
import ejbs.interfaces.ITableBean;
import jakarta.ejb.Stateless;

@Stateless(name = "TableEJB")
public class TableBean implements ITableBean {
    public TableBean() {
    }

    @Override
    public Table createTable(Table table) {
        ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());

        return iTableDao.createTable(table);
    }
}
