package exceptions;

public class TableNotFoundException extends Exception {
    public TableNotFoundException() {
        super();
    }

    public TableNotFoundException(String tableId) {
        super("Table with id: " + tableId + " Not Found");
    }
}
