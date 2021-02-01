package ejbs.interfaces;

import entities.Table;
import jakarta.ejb.Remote;

import java.util.Collection;

@Remote
public interface ITableBean {
    Table createTable(Table table);

    Collection<Table> getTables();

    Table bookTable(String idTable);
}
