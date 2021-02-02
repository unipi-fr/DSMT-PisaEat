package ejbs.interfaces;

import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.Remote;

import java.util.Collection;

@Remote
public interface ITableBean {
    Table createTable(Table table);

    Collection<Table> getTables();

    Table bookTable(String tableId, String name) throws TableNotFoundException, TableAlreadyBookedException;

    BookSession getBookSessionByTable(Table table) throws BookSessionNotFoundException;

    BookSession joinBookSession(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException;

    BookSession getBookSessionById(String id) throws BookSessionNotFoundException;
}
