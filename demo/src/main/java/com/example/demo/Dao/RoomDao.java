package com.example.demo.Dao;
import com.example.demo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RoomDao extends JpaRepository<Room, Integer> {

    List<Room> findByRoomCapacity(int capacity);

    Room findByRoomName(String roomName);
    // You can add custom query methods here if needed
    Room findById(int roomId);
}
