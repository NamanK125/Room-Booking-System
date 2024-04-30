package com.example.demo.Controller;
import com.example.demo.Service.RoomService;
import com.example.demo.Service.UserService;
import org.springframework.http.HttpStatus;
import com.example.demo.Service.BookingService;
import com.example.demo.Model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/book")
public class BookingController{
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody Booking booking){
//        if (!userService.findByUserId(booking.getUserId())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
//        }
//        if (!roomService.findByRoomId(booking.getRoomId())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room does not exist");
//        }
        bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking successful");}

    @PatchMapping
    public ResponseEntity<String> editBooking(@RequestBody Booking booking){
        if (!userService.existsByUserId(booking.getUserId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }
        if (!roomService.existsByRoomId(booking.getRoomId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room does not exist");
        }
        if (!bookingService.existsByBookingId(booking.getBookingId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking does not exist");
        }
        if(!bookingService.isBookingTimeOverlap(booking.getRoomId(), booking.getDateOfBooking(), booking.getTimeFrom(), booking.getTimeTo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room unavailable");
        }

        bookingService.updateBooking(booking);
        return ResponseEntity.status(HttpStatus.OK).body("Booking modified successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBooking(@RequestParam int bookingId){
        if (!bookingService.existsByBookingId(bookingId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking does not exist");
        }

        bookingService.deleteBooking(bookingId);
        return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully");
    }
}
