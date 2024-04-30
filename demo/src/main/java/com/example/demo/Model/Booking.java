package com.example.demo.Model;
import jakarta.persistence.*;

import java.util.Date;

@Table(
        name = "BOOKING"
)
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int roomId;
    private Date dateOfBooking;
    private String timeFrom;
    private String timeTo;
    private String purpose;

    // Constructors, getters, and setters

    public Booking() {
        // Default constructor
    }

    public Booking(int userId, int roomId, Date dateOfBooking, String timeFrom, String timeTo, String purpose) {
        this.userId = userId;
        this.roomId = roomId;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;
    }

    // Getters and setters

    public int getBookingId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}


