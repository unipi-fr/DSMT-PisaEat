package entities;

import java.util.ArrayList;
import java.io.Serializable;

public class BookSession implements Serializable {
    private String id;
    private String pin;
    private ArrayList<String> users;
    private String booker;


    public BookSession(String id, String pin, ArrayList<String> users, String booker) {
        this.setId(id);
        this.setPin(pin);
        setUsers(users);
        this.setBooker(booker);
    }

    public BookSession(String booker) {
        this.booker = booker;
        users = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
