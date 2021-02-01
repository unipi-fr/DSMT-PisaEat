package ejbs.interfaces;

import database.exceptions.TableNotFoundException;
import entities.Table;
import exceptions.TableAlreadyBookedException;
import jakarta.ejb.Remote;

import java.util.Collection;

@Remote
public interface ITableBean {
    Table createTable(Table table);

    Collection<Table> getTables();

    Table bookTable(String idTable, String name) throws TableNotFoundException, TableAlreadyBookedException;
}
