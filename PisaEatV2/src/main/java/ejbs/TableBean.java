package ejbs;

import database.IBookSessionDao;
import database.ITableDao;
import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import database.mongo.BookSessionDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import entities.BookSession;
import entities.Table;
import ejbs.interfaces.ITableBean;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.Stateless;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.logging.Logger;

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
        String pin = String.format("%04d", num);

        return pin;
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
    public Table bookTable(String idTable, String name) throws TableNotFoundException, TableAlreadyBookedException {
        Logger logger = Logger.getLogger(getClass().getName());

        BookSession bookSession = new BookSession(name, generatePin());

        Table table = iTableDao.getTableById(idTable);

        if(table.getBookSessionId() != null) {
            logger.info("[DEBUG] " + table.getBookSessionId());
            throw new TableAlreadyBookedException();
        }

        logger.info("[DEBUG] " + table.getName());

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
    public BookSession getBookSessionById(String id) throws BookSessionNotFoundException {
        return iBookSessionDao.getBookSessionById(id);
    }
}
