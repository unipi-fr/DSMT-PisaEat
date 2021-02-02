package entities;

import java.util.ArrayList;
import java.io.Serializable;

public class BookSession implements Serializable {
    private String id;
    private String pin;
    private ArrayList<String> users;
    private String bookingName;


    public BookSession(String id, String pin, ArrayList<String> users, String bookingName) {
        this.setId(id);
        this.setPin(pin);
        setUsers(users);
        this.setBookingName(bookingName);
    }

    public BookSession(String bookingName, String pin) {
        this.bookingName = bookingName;
        this.pin = pin;

        users = new ArrayList<>();
        users.add(bookingName);
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

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }
}
