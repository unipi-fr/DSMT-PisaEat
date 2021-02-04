package ejbs.tasks;

import com.google.common.util.concurrent.Striped;
import database.IBookSessionDao;
import database.ITableDao;
import database.mongo.BookSessionDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import entities.BookSession;
import entities.Table;
import exceptions.TableAlreadyBookedException;

import java.security.SecureRandom;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class BookTableTask implements Callable<Table> {
    private static final Striped<Lock> tableLocks = Striped.lock(1024);
    private final ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());
    private final IBookSessionDao iBookSessionDao = new BookSessionDao(MongoDbConnector.getInstance());

    private String tableId;
    private String name;

    public BookTableTask(String tableId, String name){
        this.tableId = tableId;
        this.name = name;
    }

    private String generatePin() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000);

        return String.format("%04d", num);
    }

    @Override
    public Table call() throws Exception {
        if (name == null) {
            throw new IllegalArgumentException();
        }

        BookSession bookSession = new BookSession(name, generatePin());

        tableLocks.get(tableId).lock();

        try {

            Table table = iTableDao.getTableById(tableId);

            if (table.getBookSessionId() != null) {
                throw new TableAlreadyBookedException();
            }

            bookSession = iBookSessionDao.createBookSession(bookSession);

            table.setBookSessionId(bookSession.getId());

            Table returnTable = iTableDao.updateTable(table);

            tableLocks.get(tableId).unlock();
            return returnTable;
        }catch (Exception e){
            tableLocks.get(tableId).unlock();
            throw e;
        }

    }
}
