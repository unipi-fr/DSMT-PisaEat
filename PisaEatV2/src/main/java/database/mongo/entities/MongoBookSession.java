package database.mongo.entities;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class MongoBookSession {
    private ObjectId id;
    private String pin;
    private ArrayList<String> users;
    private String booker;


    public MongoBookSession(ObjectId id, String pin, ArrayList<String> users, String booker) {
        this.setId(id);
        this.setPin(pin);
        setUsers(users);
        this.setBooker(booker);
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
        this.users = users;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}

