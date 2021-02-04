package ejbs;

import database.IBookSessionDao;
import database.ITableDao;
import database.mongo.BookSessionDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
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

    private String generatePin() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000);

        return String.format("%04d", num);
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
}
