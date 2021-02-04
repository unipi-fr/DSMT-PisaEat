package exceptions;

public class InvalidPinException extends Exception {
    public InvalidPinException() {
        super("The pin is not correct");
    }
}
