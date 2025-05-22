package com.example.reservationRequest.Entity;

public class ReservationResponse {
    private String reservationId;
    private String status;
    private String tableId;
    private String waitlistMessage;

    public ReservationResponse(String reservationId, String status, String tableId, String waitlistMessage) {
        this.reservationId = reservationId;
        this.status = status;
        this.tableId = tableId;
        this.waitlistMessage = waitlistMessage;
    }

    public ReservationResponse() {
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getWaitlistMessage() {
        return waitlistMessage;
    }

    public void setWaitlistMessage(String waitlistMessage) {
        this.waitlistMessage = waitlistMessage;
    }
}
