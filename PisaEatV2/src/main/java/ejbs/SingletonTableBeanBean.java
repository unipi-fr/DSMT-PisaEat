package ejbs;

import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import ejbs.interfaces.ISingletonTableBean;
import ejbs.tasks.CreateTableTask;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.Asynchronous;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import java.util.Collection;
import java.util.concurrent.Future;

@Singleton(mappedName = "SingletonTableEJB")
public class SingletonTableBeanBean implements ISingletonTableBean {

    @SuppressWarnings("EjbEnvironmentInspection")
    @Resource(name = "java:comp/DefaultManagedThreadFactory")
    ManagedThreadFactory threadFactory;

    @Resource(name = "java:comp/DefaultManagedExecutorService")
    ManagedExecutorService executorService;

    public SingletonTableBeanBean() {
    }

    @Override
    @Asynchronous
    public Future<Table> createTable(Table table) {
        return executorService.submit(new CreateTableTask(table));
    }

    @Override
    public Future<Collection<Table>> getTables() {
        return null;
    }

    @Override
    public Future<Table> bookTable(String tableId, String name) throws TableNotFoundException, TableAlreadyBookedException {
        return null;
    }

    @Override
    public Future<BookSession> getBookSessionByTable(Table table) throws BookSessionNotFoundException {
        return null;
    }

    @Override
    public Future<BookSession> joinBookSession(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException {
        return null;
    }

    @Override
    public Future<BookSession> getBookSessionById(String id) throws BookSessionNotFoundException {
        return null;
    }
}
