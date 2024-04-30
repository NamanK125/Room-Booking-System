package com.example.demo.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(
        name = "USER_DATA"
)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int Id;
    private String email;
    private String name;
    private String password;


    // Getters and setters

    public void setId(int id) {
        this.Id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }
}
