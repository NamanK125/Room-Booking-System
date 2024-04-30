package com.example.demo.Controller;
import com.example.demo.Model.Booking;
import com.example.demo.Model.BookingDetail;
import com.example.demo.Service.BookingService;
import com.example.demo.Service.RoomService;
import com.example.demo.Model.User;
import com.example.demo.Model.UserDetail;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            String email = user.getEmail();
            String password = user.getPassword();
            String name = user.getName();
            if(userService.findByEmail(user.getEmail()) != null) {
                return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
            }
            else{
                userService.createUser(user);
                return ResponseEntity.ok("Account creation successful");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            if (userService.loginUser(user.getEmail(), user.getPassword())) {
                // Authentication successful
                return ResponseEntity.ok("Login successful");
            } else {
                // Authentication failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestParam int userID) {
        User user = userService.getUserById(userID);
        if (user == null) {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        } else {
            UserDetail userDetail = new UserDetail(user.getEmail(), user.getId(), user.getName());
            return new ResponseEntity<>(userDetail, HttpStatus.OK);
        }
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDetail>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDetail> userDetails = users.stream()
                .map(user -> new UserDetail(user.getEmail(), user.getId(), user.getName()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

//
@GetMapping("/history")
public ResponseEntity<?> getBookingHistory(@RequestParam int userID) {
    try {
        List<Booking> bookings = bookingService.getBookingsByUserId(userID);
        if (bookings.isEmpty()) {
            return new ResponseEntity<>("No bookings found", HttpStatus.NOT_FOUND);
        }
        List<BookingDetail> bookingDetails = bookings.stream()
                .map(booking -> new BookingDetail(booking.getBookingId(), booking.getRoomId(), booking.getUserId(), booking.getDateOfBooking(), booking.getTimeFrom(), booking.getTimeTo()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingBookingsByUserId(@RequestParam int userID) {
        try {
            if (!userService.existsByUserId(userID)) {
                return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
            }
            List<Booking> upcomingBookings = bookingService.getUpcomingBookingsByUserId(userID);
            List<BookingDetail> bookingDetails = new ArrayList<>();
            for (Booking booking : upcomingBookings) {
                bookingDetails.add(new BookingDetail(booking, roomService));
            }
            return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
