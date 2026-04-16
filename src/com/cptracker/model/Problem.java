package com.cptracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Problem Entity Class
 * Represents a coding problem in the CP Tracker system
 */
public class Problem {
    
    // Private fields
    private int problemId;
    private int userId;
    private String title;
    private String platform;
    private String difficulty;  // EASY, MEDIUM, HARD
    private String status;      // SOLVED, ATTEMPTED, TODO
    private LocalDate solvedDate;
    private int attempts;
    private String notes;
    private LocalDateTime createdAt;
    
    // Default constructor
    public Problem() {
    }
    
    // Constructor without problemId (for new problems)
    public Problem(int userId, String title, String platform, String difficulty, 
                   String status, LocalDate solvedDate, int attempts, String notes) {
        this.userId = userId;
        this.title = title;
        this.platform = platform;
        this.difficulty = difficulty;
        this.status = status;
        this.solvedDate = solvedDate;
        this.attempts = attempts;
        this.notes = notes;
    }
    
    // Constructor with all fields
    public Problem(int problemId, int userId, String title, String platform, 
                   String difficulty, String status, LocalDate solvedDate, 
                   int attempts, String notes, LocalDateTime createdAt) {
        this.problemId = problemId;
        this.userId = userId;
        this.title = title;
        this.platform = platform;
        this.difficulty = difficulty;
        this.status = status;
        this.solvedDate = solvedDate;
        this.attempts = attempts;
        this.notes = notes;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getProblemId() {
        return problemId;
    }
    
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getSolvedDate() {
        return solvedDate;
    }
    
    public void setSolvedDate(LocalDate solvedDate) {
        this.solvedDate = solvedDate;
    }
    
    public int getAttempts() {
        return attempts;
    }
    
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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
        return "Problem{" +
                "problemId=" + problemId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", status='" + status + '\'' +
                ", solvedDate=" + solvedDate +
                ", attempts=" + attempts +
                ", notes='" + notes + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
