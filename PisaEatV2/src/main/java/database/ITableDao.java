package database;

import database.exceptions.TableNotFoundException;
import entities.Table;

import java.util.Collection;

public interface ITableDao {
    Table createTable(Table table) throws IllegalArgumentException;

    Collection<Table> getTables();

    Table getTableById(String tableId) throws TableNotFoundException, IllegalArgumentException;

    Table updateTable(Table table) throws TableNotFoundException;
}
