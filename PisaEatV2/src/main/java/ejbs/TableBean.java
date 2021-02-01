package ejbs;

import database.ITableDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import entities.Table;
import ejbs.interfaces.ITableBean;
import jakarta.ejb.Stateless;

import java.util.Collection;

//@Stateless(name = "TableEJB")
@Stateless
public class TableBean implements ITableBean {
    private final ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());

    public TableBean() {
    }

    @Override
    public Table createTable(Table table) {
        return iTableDao.createTable(table);
    }

    @Override
    public Collection<Table> getTables() {
        return iTableDao.getTables();
    }

    @Override
    public Table bookTable(String idTable) {
        return null;
    }
}
