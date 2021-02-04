package exceptions;

public class TableAlreadyBookedException extends Exception{
    public TableAlreadyBookedException() {
        super();
    }

    public TableAlreadyBookedException(String tableName) {
        super("Table with name: " + tableName + " Already Booked");
    }
}
