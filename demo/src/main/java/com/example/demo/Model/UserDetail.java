package com.example.demo.Model;

public class UserDetail {
    private String email;
    private int id;
    private String name;

    // Constructor, getters and setters
    public UserDetail(String email, int id, String name) {
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public UserDetail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}