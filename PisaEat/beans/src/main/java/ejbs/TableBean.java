package ejbs;

import Dao.MongoDbConnector;
import Dao.TableDao;
import database.ITableDao;
import entities.Table;
import interfaces.ITableBean;
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
