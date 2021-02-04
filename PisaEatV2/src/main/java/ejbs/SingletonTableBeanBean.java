package ejbs;

import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import ejbs.interfaces.ISingletonTableBean;
import ejbs.tasks.CreateTableTask;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import jakarta.annotation.Resource;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.concurrent.ManagedThreadFactory;

import java.util.Collection;
import java.util.concurrent.Future;

@Singleton(name = "SingletonTableEJB")
public class SingletonTableBeanBean implements ISingletonTableBean {

    @SuppressWarnings("EjbEnvironmentInspection")
    @Resource(name = "concurrent/__defaultManagedThreadFactory")
    ManagedThreadFactory threadFactory;

    @SuppressWarnings("EjbEnvironmentInspection")
    @Resource(name = "concurrent/__defaultManagedExecutorService")
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
