package com.example.demo.Controller;
import com.example.demo.Model.Room;
import com.example.demo.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<?> getRooms(@RequestParam(required = false) Integer capacity) {
        List<Room> rooms;
        if (capacity != null && capacity <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid capacity parameter");
        }
        if (capacity != null) {
            rooms = roomService.getRoomsByCapacity(capacity);
        } else {
            rooms = roomService.getAllRooms();
        }
        return ResponseEntity.ok(rooms);
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody Room r){
        if (r.getRoomCapacity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid capacity");
        }
        if (roomService.isRoomNameExists(r.getRoomName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room already exists");
        }
        roomService.addRoom(r.getRoomCapacity(), r.getRoomName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Room created successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoom(@RequestParam int roomId) {
        if(roomService.deleteRoom(roomId)){
        return ResponseEntity.status(HttpStatus.OK).body("Room deleted successfully");
    }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    @PatchMapping
    public ResponseEntity<?> editRoom(@RequestBody Room room) {
        if (room.getRoomCapacity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid capacity");
        }
        Room r = roomService.getRoomById(room.getRoomId());
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
        r.setRoomName(room.getRoomName());
        r.setRoomCapacity(room.getRoomCapacity());
        return ResponseEntity.status(HttpStatus.OK).body("Room updated successfully");
    }
}