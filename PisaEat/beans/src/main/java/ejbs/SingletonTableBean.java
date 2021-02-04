package ejbs;

import ejbs.tasks.BookTableTask;
import ejbs.tasks.JoinBookSessionTask;
import entities.BookSession;
import entities.Table;
import interfaces.ISingletonTableBean;
import jakarta.annotation.Resource;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.enterprise.concurrent.ManagedExecutorService;

import java.util.concurrent.Future;

@Singleton(name = "SingletonTableEJB")
public class SingletonTableBean implements ISingletonTableBean {

    @Resource(name = "concurrent/__defaultManagedExecutorService")
    ManagedExecutorService executorService;

    public SingletonTableBean() {
    }

    @Override
    @Asynchronous
    public Future<Table> bookTable(String tableId, String name) {
        return executorService.submit(new BookTableTask(tableId, name));
    }

    @Override
    @Asynchronous
    public Future<BookSession> joinBookSession(String bookSessionId, String name, String pin) {
        return executorService.submit(new JoinBookSessionTask(bookSessionId, name, pin));
    }
}
