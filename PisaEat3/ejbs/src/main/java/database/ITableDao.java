package database;

import entities.Table;
import exceptions.TableNotFoundException;

import java.util.Collection;

public interface ITableDao {
    Table createTable(Table table) throws IllegalArgumentException;

    Collection<Table> getTables();

    Table getTableById(String tableId) throws TableNotFoundException, IllegalArgumentException;

    Table getTableBySessionId(String bookSessionId) throws TableNotFoundException, IllegalArgumentException;

    Table updateTable(Table table) throws TableNotFoundException;
}
