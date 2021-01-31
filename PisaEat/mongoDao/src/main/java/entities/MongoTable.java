package entities;

import org.bson.types.ObjectId;

public class MongoTable {
    private ObjectId id;
    private String name;
    private int numberOfSeat;
    private ObjectId bookSessionId;

    public MongoTable() {
    }

    public MongoTable(String name, int numberOfSeat, ObjectId bookSessionId) {
        this.name = name;
        this.numberOfSeat = numberOfSeat;
        this.bookSessionId = bookSessionId;
    }

    public MongoTable(ObjectId id, String name, int numberOfSeat, ObjectId bookSessionId) {
        this.id = id;
        this.name = name;
        this.numberOfSeat = numberOfSeat;
        this.bookSessionId = bookSessionId;
    }

    public ObjectId getBookSessionId() {
        return bookSessionId;
    }

    public void setBookSessionId(ObjectId bookSessionId) {
        this.bookSessionId = bookSessionId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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