package com.example.demo.Dao;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    // Custom query method to find a user by email
    User findByEmail(String email);
}