package com.example.demo.Service;

import com.example.demo.Dao.BookingDao;
import com.example.demo.Model.Booking;
import com.example.demo.Model.BookingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public List<BookingDetail> getBookingHistoryByUser(int userID){
       return getAllBookingsWithoutUserId(userID);
    }

    public List<BookingDetail> getAllBookingsWithoutUserId(int userId) {
        return bookingDao.findByUserId(userId).stream()
                .map(booking -> {
                    BookingDetail response = new BookingDetail();
                    response.setBookingID(booking.getBookingId());
                    response.setRoomID(booking.getRoomId());
                    response.setDateOfBooking(booking.getDateOfBooking());
                    response.setTimeFrom(booking.getTimeFrom());
                    response.setTimeTo(booking.getTimeTo());
                    response.setPurpose(booking.getPurpose());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public void createBooking(Booking booking) {
        Booking booking1 = new Booking();
        booking1.setUserId(booking.getUserId());
        booking1.setRoomId(booking.getRoomId());
        booking1.setDateOfBooking(booking.getDateOfBooking());
        booking1.setTimeFrom(booking.getTimeFrom());
        booking1.setTimeTo(booking.getTimeTo());
        booking1.setPurpose(booking.getPurpose());
        bookingDao.save(booking1);
    }

    public boolean updateBooking(Booking booking) {
        if (bookingDao.existsById(booking.getBookingId())) {
            bookingDao.save(booking);
            return true;
        }
        return false;
    }

    public boolean existsByBookingId(int bookingId) {
        if(bookingDao.existsById(bookingId))
            return true;
        return false;
    }

    public boolean isBookingTimeOverlap(int roomId, Date dateOfBooking, String timeFrom, String timeTo) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date newBookingTimeFrom;
        Date newBookingTimeTo;
        try {
            newBookingTimeFrom = sdf.parse(timeFrom);
            newBookingTimeTo = sdf.parse(timeTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid time format");
        }

        List<Booking> bookingsOnSameDateAndRoom = bookingDao.findByRoomIdAndDateOfBooking(roomId, dateOfBooking);
        for (Booking booking : bookingsOnSameDateAndRoom) {
            Date existingBookingTimeFrom;
            Date existingBookingTimeTo;
            try {
                existingBookingTimeFrom = sdf.parse(booking.getTimeFrom());
                existingBookingTimeTo = sdf.parse(booking.getTimeTo());
            } catch (ParseException e) {
                throw new RuntimeException("Invalid time format in existing booking");
            }

            if ((newBookingTimeFrom.after(existingBookingTimeFrom) && newBookingTimeFrom.before(existingBookingTimeTo)) ||
                    (newBookingTimeTo.after(existingBookingTimeFrom) && newBookingTimeTo.before(existingBookingTimeTo)) ||
                    (newBookingTimeFrom.before(existingBookingTimeFrom) && newBookingTimeTo.after(existingBookingTimeTo))) {
                return true;
            }
        }

        return false;
    }

    public void deleteBooking(int bookingId) {
        if(bookingDao.existsById(bookingId))
            bookingDao.deleteById(bookingId);
        else
            throw new RuntimeException("Booking does not exist");
    }

    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        for(Booking booking : bookingDao.findAll()){
            if(booking.getUserId() == userId){
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // In BookingService.java
    public List<Booking> getUpcomingBookingsByUserId(int userId) {
        List<Booking> allBookings = getBookingsByUserId(userId);
        Date now = new Date();
        List<Booking> upcomingBookings = new ArrayList<>();
        for (Booking booking : allBookings) {
            if (booking.getDateOfBooking().after(now)) {
                upcomingBookings.add(booking);
            }
        }
        return upcomingBookings;
    }
}