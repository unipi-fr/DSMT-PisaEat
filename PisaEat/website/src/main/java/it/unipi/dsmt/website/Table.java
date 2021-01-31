package it.unipi.dsmt.website;

public class Table {
    private String name;
    private int numberOfSeat;

    public Table(String name, int numberOfSeat) {
        this.name = name;
        this.numberOfSeat = numberOfSeat;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

}
