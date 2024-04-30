package com.example.demo.Service;
import com.example.demo.Dao.BookingDao;
import com.example.demo.Model.Booking;
import com.example.demo.Model.BookingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.UserDao;
import com.example.demo.Model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserServiceImpl userServiceImpl;
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    @Override
    public void createUser(User user) {
        if(findByEmail(user.getEmail())!=null)
            throw new RuntimeException("User email already exists");
            userDao.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    @Override
    public User getUserById(int userId) {
        return userDao.findById(userId).orElse(null);
    }


    @Override
    public boolean deleteUser(int userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = userDao.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean findByUserId(int userId) {
        return userDao.existsById(userId);
    }

    @Override
    public boolean existsByUserId(int userId) {
        return userDao.existsById(userId);
    }
}
