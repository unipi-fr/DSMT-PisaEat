package interfaces;

import entities.BookSession;
import entities.Table;
import exceptions.BookSessionNotFoundException;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import exceptions.TableNotFoundException;
import jakarta.ejb.Remote;

import java.util.concurrent.Future;

@Remote
public interface ISingletonTableBean {

    Future<Table> bookTable(String tableId, String name) throws TableNotFoundException, TableAlreadyBookedException;

    Future<BookSession> joinBookSession(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException;
}
