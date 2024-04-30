package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.Booking;
import com.example.demo.Model.BookingDetail;
import com.example.demo.Model.User;
public interface UserService {
    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(int userId);

    User findByEmail(String email);

    boolean findByUserId(int userId);
    boolean deleteUser(int userId);

    boolean loginUser(String email, String password);


    boolean existsByUserId(int userId);
}
