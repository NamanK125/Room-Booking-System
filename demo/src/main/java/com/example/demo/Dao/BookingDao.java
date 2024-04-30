package com.example.demo.Dao;
import com.example.demo.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Integer>{
    List<Booking> findByUserId(int userID);

    Collection<Date> findByRoomId(int roomId);

    List<Booking> findByRoomIdAndDateOfBooking(int roomId, Date dateOfBooking);
}
