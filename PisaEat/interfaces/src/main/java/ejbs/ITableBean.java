package ejbs;

import entities.Table;
import jakarta.ejb.Remote;

@Remote
public interface ITableBean {
    Table createTable(Table table);
}
