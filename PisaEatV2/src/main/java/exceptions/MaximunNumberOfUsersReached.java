package exceptions;

public class MaximunNumberOfUsersReached extends Exception {
    public MaximunNumberOfUsersReached() {
        super();
    }

    public MaximunNumberOfUsersReached(String message) {
        super(message);
    }

    public MaximunNumberOfUsersReached(String tableName, int numberOfSeat) {
        super("For table '" + tableName + "' reached maximun nymber of seat of " + numberOfSeat);
    }
}
