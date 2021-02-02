package ejbs;

import database.IBookSessionDao;
import database.ITableDao;
import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import database.mongo.BookSessionDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import ejbs.interfaces.ITableBean;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
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
    public Table bookTable(String tableId, String name) throws TableNotFoundException, TableAlreadyBookedException {
        if (name == null) {
            throw new IllegalArgumentException();
        }

        BookSession bookSession = new BookSession(name, generatePin());

        Table table = iTableDao.getTableById(tableId);

        if (table.getBookSessionId() != null) {
            throw new TableAlreadyBookedException();
        }

        bookSession = iBookSessionDao.createBookSession(bookSession);

        table.setBookSessionId(bookSession.getId());

        return iTableDao.updateTable(table);
    }

    @Override
    public BookSession getBookSessionByTable(Table table) throws BookSessionNotFoundException {
        if (table == null) {
            throw new IllegalArgumentException();
        }

        return getBookSessionById(table.getBookSessionId());
    }

    @Override
    public BookSession joinBookSession(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException {
        if (bookSessionId == null || name == null || pin == null) {
            throw new IllegalArgumentException();
        }

        BookSession bookSession = getBookSessionById(bookSessionId);

        if (!bookSession.getPin().equals(pin)) {
            throw new InvalidPinException();
        }

        iBookSessionDao.addUserToBookSession(bookSessionId, name);

        return getBookSessionById(bookSessionId);
    }

    @Override
    public BookSession getBookSessionById(String id) throws BookSessionNotFoundException {
        return iBookSessionDao.getBookSessionById(id);
    }
}
