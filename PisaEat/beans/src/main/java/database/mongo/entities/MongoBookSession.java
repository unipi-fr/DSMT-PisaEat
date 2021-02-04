package database.mongo.entities;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class MongoBookSession {
    private ObjectId id;
    private String pin;
    private ArrayList<String> users;
    private String bookingName;

    public MongoBookSession() {
    }

    public MongoBookSession(String pin, ArrayList<String> users, String bookingName) {
        this.setPin(pin);
        setUsers(users);
        this.setBookingName(bookingName);
    }

    public MongoBookSession(ObjectId id, String pin, ArrayList<String> users, String bookingName) {
        this.setId(id);
        this.setPin(pin);
        setUsers(users);
        this.setBookingName(bookingName);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = new ArrayList<>(users);
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }
}

