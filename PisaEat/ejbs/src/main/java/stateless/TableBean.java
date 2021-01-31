package stateless;

import Dao.MongoDbConnector;
import Dao.TableDao;
import database.ITableDao;
import ejbs.ITableBean;
import entities.Table;

@javax.ejb.Stateless(name = "TableEJB")
public class TableBean implements ITableBean {

    private final ITableDao tableDao;

    public TableBean() {
        this.tableDao = new TableDao(MongoDbConnector.getInstance());
    }

    @Override
    public Table createTable(Table table) {
        return this.tableDao.createTable(table);
    }
}
