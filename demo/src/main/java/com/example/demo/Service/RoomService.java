package com.example.demo.Service;
import com.example.demo.Model.Room;
import com.example.demo.Dao.RoomDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class RoomService {
    @Autowired
    private RoomDao roomDao;

    public Room getRoomById(int roomId) {
        Room room = roomDao.findById(roomId);
        if(room != null)
            return room;
        else
            throw new RuntimeException("Room not found");
    };

    public String getRoomNameById(int roomId) {
        Room room = getRoomById(roomId);
        return room.getRoomName();
    }
    public List<Room> getRoomsByCapacity(int capacity)
    {
        List<Room> rooms = roomDao.findByRoomCapacity(capacity);
        List<Room> roomsByCapacity = new ArrayList<>();
        for(Room room : rooms){
            if(room.getCapacity() == capacity){
                roomsByCapacity.add(room);
            }
        }
        return roomsByCapacity;
    }
    public List<Room> getAllRooms(){
        return roomDao.findAll();
    }

    RoomService roomService;

    public boolean isRoomNameExists(String roomName) {
        return roomDao.findByRoomName(roomName) != null;
    }

    public void addRoom(int capacity, String roomName) {
        Room room = new Room();
        room.setCapacity(capacity);
        room.setRoomName(roomName);
        roomDao.save(room);
    }

    public boolean deleteRoom(int roomId) {
        if(roomDao.existsById(roomId))
        {
            roomDao.deleteById(roomId);
            return true;
        }
        else
            return false;

    }

    public boolean findByRoomId(int roomId) {
        int id = roomId;
        return roomDao.existsById(id);
    }

    public boolean existsByRoomId(int roomId) {
        return roomDao.existsById(roomId);
    }
}