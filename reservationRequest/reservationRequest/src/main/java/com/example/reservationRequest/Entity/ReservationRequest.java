package com.example.reservationRequest.Entity;

import java.util.List;

public class ReservationRequest {
    private String tableId;
    private String customerId;
    private String reservationType; // "reserve" or "cancel"
    private List<String> preferences;

    public ReservationRequest(String tableId, String customerId, String reservationType, List<String> preferences) {
        this.tableId = tableId;
        this.customerId = customerId;
        this.reservationType = reservationType;
        this.preferences = preferences;
    }

    public ReservationRequest() {
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
    // Getters, setters
}
