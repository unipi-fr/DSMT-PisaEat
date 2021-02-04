package ejbs.tasks;

import com.google.common.util.concurrent.Striped;
import database.IBookSessionDao;
import database.ITableDao;
import database.mongo.BookSessionDao;
import database.mongo.MongoDbConnector;
import database.mongo.TableDao;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.MaximunNumberOfUsersReached;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class JoinBookSessionTask implements Callable<BookSession> {
    private static final Striped<Lock> sessionLocks = Striped.lock(1024);
    private final ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());
    private final IBookSessionDao iBookSessionDao = new BookSessionDao(MongoDbConnector.getInstance());
    private String bookSessionId;
    private String name;
    private String pin;

    public JoinBookSessionTask(String bookSessionId, String name, String pin) {
        this.bookSessionId = bookSessionId;
        this.name = name;
        this.pin = pin;
    }

    @Override
    public BookSession call() throws Exception {
        if (bookSessionId == null || name == null || pin == null) {
            throw new IllegalArgumentException();
        }


        sessionLocks.get(bookSessionId).lock();
        try {
            BookSession bookSession = iBookSessionDao.getBookSessionById(bookSessionId);

            if (!bookSession.getPin().equals(pin)) {
                throw new InvalidPinException();
            }

            Table table = iTableDao.getTableBySessionId(bookSessionId);
            ArrayList<String> users = bookSession.getUsers();

            boolean alreadySit = users.contains(name);

            if(!alreadySit){
                if(!(table.getNumberOfSeat() > users.size())){
                    throw new MaximunNumberOfUsersReached(table.getName(), table.getNumberOfSeat());
                }
                iBookSessionDao.addUserToBookSession(bookSessionId, name);
            }

            bookSession = iBookSessionDao.getBookSessionById(bookSessionId);
            sessionLocks.get(bookSessionId).unlock();
            return bookSession;
        }catch (Exception e){
            sessionLocks.get(bookSessionId).unlock();
            throw e;
        }
    }
}
