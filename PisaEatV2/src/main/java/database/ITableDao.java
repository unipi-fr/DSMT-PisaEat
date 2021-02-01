package database;

import entities.Table;

public interface ITableDao {
    Table createTable(Table table) throws IllegalArgumentException;

    //Iterable<Table> getTables();
}
