package com.cptracker.model;

import java.time.LocalDateTime;

/**
 * User Entity Class
 * Represents a user in the CP Tracker system
 */
public class User {
    
    // Private fields
    private int userId;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    
    // Default constructor
    public User() {
    }
    
    // Constructor without userId (for new users)
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    // Constructor with all fields
    public User(int userId, String username, String email, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
