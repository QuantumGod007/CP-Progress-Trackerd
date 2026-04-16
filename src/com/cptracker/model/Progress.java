package com.cptracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Progress Entity Class
 * Represents user progress statistics in the CP Tracker system
 */
public class Progress {
    
    // Private fields
    private int progressId;
    private int userId;
    private int totalSolved;
    private int currentStreak;
    private int longestStreak;
    private double accuracy;
    private LocalDate lastSolvedDate;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Progress() {
    }
    
    // Constructor without progressId (for new progress)
    public Progress(int userId, int totalSolved, int currentStreak, 
                    int longestStreak, double accuracy, LocalDate lastSolvedDate) {
        this.userId = userId;
        this.totalSolved = totalSolved;
        this.currentStreak = currentStreak;
        this.longestStreak = longestStreak;
        this.accuracy = accuracy;
        this.lastSolvedDate = lastSolvedDate;
    }
    
    // Constructor with all fields
    public Progress(int progressId, int userId, int totalSolved, int currentStreak, 
                    int longestStreak, double accuracy, LocalDate lastSolvedDate, 
                    LocalDateTime updatedAt) {
        this.progressId = progressId;
        this.userId = userId;
        this.totalSolved = totalSolved;
        this.currentStreak = currentStreak;
        this.longestStreak = longestStreak;
        this.accuracy = accuracy;
        this.lastSolvedDate = lastSolvedDate;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public int getProgressId() {
        return progressId;
    }
    
    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getTotalSolved() {
        return totalSolved;
    }
    
    public void setTotalSolved(int totalSolved) {
        this.totalSolved = totalSolved;
    }
    
    public int getCurrentStreak() {
        return currentStreak;
    }
    
    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }
    
    public int getLongestStreak() {
        return longestStreak;
    }
    
    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }
    
    public double getAccuracy() {
        return accuracy;
    }
    
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
    
    public LocalDate getLastSolvedDate() {
        return lastSolvedDate;
    }
    
    public void setLastSolvedDate(LocalDate lastSolvedDate) {
        this.lastSolvedDate = lastSolvedDate;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // toString method
    @Override
    public String toString() {
        return "Progress{" +
                "progressId=" + progressId +
                ", userId=" + userId +
                ", totalSolved=" + totalSolved +
                ", currentStreak=" + currentStreak +
                ", longestStreak=" + longestStreak +
                ", accuracy=" + accuracy +
                ", lastSolvedDate=" + lastSolvedDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
