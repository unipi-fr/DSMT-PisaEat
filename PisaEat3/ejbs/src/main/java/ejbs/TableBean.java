package ejbs;

import database.IBookSessionDao;
import database.ITableDao;
import database.mongo.BookSessionDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import exceptions.TableNotFoundException;
import interfaces.ITableBean;
import entities.BookSession;
import entities.Table;
import exceptions.BookSessionNotFoundException;
import jakarta.ejb.Stateless;

import java.security.SecureRandom;
import java.util.Collection;

//@Stateless(name = "TableEJB")
@Stateless
public class TableBean implements ITableBean {
    private final ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());
    private final IBookSessionDao iBookSessionDao = new BookSessionDao(MongoDbConnector.getInstance());

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
    public BookSession getBookSessionByTable(Table table) throws BookSessionNotFoundException {
        if (table == null) {
            throw new IllegalArgumentException();
        }

        return getBookSessionById(table.getBookSessionId());
    }

    @Override
    public BookSession getBookSessionById(String id) throws BookSessionNotFoundException {
        return iBookSessionDao.getBookSessionById(id);
    }

    @Override
    public void leaveTable(String bookSessionId) throws TableNotFoundException {
        if (bookSessionId == null) {
            throw new IllegalArgumentException();
        }

        Table table = iTableDao.getTableBySessionId(bookSessionId);

        table.setBookSessionId(null);

        iTableDao.updateTable(table);
    }
}
