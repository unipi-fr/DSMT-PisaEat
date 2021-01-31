package entities;

public class Table {
    private String id;
    private String name;
    private int numberOfSeat;
    private String bookSessionId;

    public Table() {
    }

    public Table(String name, int numberOfSeat, String bookSessionId) {
        this.name = name;
        this.numberOfSeat = numberOfSeat;
        this.bookSessionId = bookSessionId;
    }

    public Table(String id, String name, int numberOfSeat, String bookSessionId) {
        this.id = id;
        this.name = name;
        this.numberOfSeat = numberOfSeat;
        this.bookSessionId = bookSessionId;
    }

    public String getBookSessionId() {
        return bookSessionId;
    }

    public void setBookSessionId(String bookSessionId) {
        this.bookSessionId = bookSessionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfSeat=" + numberOfSeat +
                ", bookSessionId=" + bookSessionId +
                '}';
    }
}