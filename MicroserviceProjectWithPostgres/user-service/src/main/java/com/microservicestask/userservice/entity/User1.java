package com.microservicestask.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "User_Info")
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String emailId;
    private String password;
    private String role;

    public User1() {
    }

    public User1(int userId, String username, String emailId, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

