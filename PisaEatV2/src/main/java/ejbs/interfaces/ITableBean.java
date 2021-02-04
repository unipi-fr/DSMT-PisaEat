package ejbs.interfaces;

import database.exceptions.BookSessionNotFoundException;
import database.exceptions.TableNotFoundException;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.Remote;

import java.util.Collection;

@SuppressWarnings("EjbInterfaceSignatureInspection")
@Remote
public interface ITableBean {
    Table createTable(Table table);

    Collection<Table> getTables();

    BookSession getBookSessionByTable(Table table) throws BookSessionNotFoundException;

    BookSession getBookSessionById(String id) throws BookSessionNotFoundException;
}
