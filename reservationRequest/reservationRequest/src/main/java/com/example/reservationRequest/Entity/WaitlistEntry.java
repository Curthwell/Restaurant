package com.example.reservationRequest.Entity;

import java.util.List;

public class WaitlistEntry {
    private String customerId;
    private List<String> preferences;

    public WaitlistEntry() {
    }

    public WaitlistEntry(String customerId, List<String> preferences) {
        this.customerId = customerId;
        this.preferences = preferences;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}
