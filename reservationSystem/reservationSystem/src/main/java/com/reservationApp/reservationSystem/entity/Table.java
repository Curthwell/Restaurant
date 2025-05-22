package com.reservationApp.reservationSystem.entity;

public class Table {
    private String tableId;
    private int capacity;
    private String location;
    private boolean available;

    public Table() {
    }

    public Table(String tableId, int capacity, String location, boolean available) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.location = location;
        this.available = available;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
// Constructors, getters, setters
}
