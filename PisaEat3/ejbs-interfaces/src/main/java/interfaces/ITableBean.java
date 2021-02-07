package interfaces;

import entities.BookSession;
import entities.Table;
import exceptions.BookSessionNotFoundException;
import exceptions.TableNotFoundException;
import jakarta.ejb.Remote;

import java.util.Collection;

@Remote
public interface ITableBean {
    Table createTable(Table table);

    Collection<Table> getTables();

    BookSession getBookSessionByTable(Table table) throws BookSessionNotFoundException;

    BookSession getBookSessionById(String id) throws BookSessionNotFoundException;

    void leaveTable(String bookSessionId) throws TableNotFoundException;
}
