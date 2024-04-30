package com.example.demo.Model;

import jakarta.persistence.*;



@Entity
@Table(
        name = "ROOM"
)public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;

    private String roomName;
    private int roomCapacity;

    // Constructors, getters, and setters

    public Room(String roomName, int roomCapacity) {
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
    }

    public Room() {

    }

    // Getters and setters

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public int getCapacity() {
        return this.roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public int getRoomId() {
    return this.roomID;
    }
}
