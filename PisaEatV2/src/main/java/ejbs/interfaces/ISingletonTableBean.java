package ejbs.interfaces;

import exceptions.BookSessionNotFoundException;
import exceptions.TableNotFoundException;
import entities.BookSession;
import entities.Table;
import exceptions.InvalidPinException;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.Remote;

import java.util.concurrent.Future;

@SuppressWarnings("EjbInterfaceSignatureInspection")
@Remote
public interface ISingletonTableBean {

    Future<Table> bookTable(String tableId, String name) throws TableNotFoundException, TableAlreadyBookedException;

    Future<BookSession> joinBookSession(String bookSessionId, String name, String pin) throws BookSessionNotFoundException, InvalidPinException;
}
