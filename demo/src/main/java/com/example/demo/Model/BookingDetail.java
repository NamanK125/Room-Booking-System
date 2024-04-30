package com.example.demo.Model;
import com.example.demo.Service.RoomService;
import java.util.Date;

public class BookingDetail {
    private String roomName;
    private int roomID;
    private int bookingID;
    private Date dateOfBooking;
    private String timeFrom;
    private String timeTo;
    private String purpose;

    private RoomService roomService;

    public BookingDetail(int bookingId, int roomId, int userId, Date dateOfBooking, String timeFrom, String timeTo) {
        this.bookingID = bookingId;
        this.roomID = roomId;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }
    public BookingDetail(Booking booking, RoomService roomService) {
        this.roomService = roomService;
        this.bookingID = booking.getBookingId();
        this.roomID = booking.getRoomId();
        this.dateOfBooking = booking.getDateOfBooking();
        this.timeFrom = booking.getTimeFrom();
        this.timeTo = booking.getTimeTo();
        this.purpose = booking.getPurpose();
        this.roomName = this.roomService.getRoomNameById(booking.getRoomId());
    }
    public BookingDetail() {

    }

    // Getters and setters...
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
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