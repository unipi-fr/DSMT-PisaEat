package exceptions;

public class BookSessionNotFoundException extends Exception {
    public BookSessionNotFoundException() {
        super();
    }

    public BookSessionNotFoundException(String bookSessionId) {
        super("BookSession with id: " + bookSessionId + " Not Found");
    }
}
