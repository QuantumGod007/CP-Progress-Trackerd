package com.cptracker.service;

import com.cptracker.dao.ProgressDAO;
import com.cptracker.dao.impl.ProgressDAOImpl;
import com.cptracker.model.Progress;

/**
 * ProgressService - Business Logic Layer
 * Handles progress statistics operations
 */
public class ProgressService {
    
    private ProgressDAO progressDAO;
    
    public ProgressService() {
        this.progressDAO = new ProgressDAOImpl();
    }
    
    // Get user progress
    public Progress getProgress(int userId) {
        return progressDAO.getProgressByUserId(userId);
    }
    
    // Create initial progress for new user
    public void createProgress(int userId) {
        Progress progress = new Progress();
        progress.setUserId(userId);
        progress.setTotalSolved(0);
        progress.setCurrentStreak(0);
        progress.setLongestStreak(0);
        progress.setAccuracy(0.0);
        progress.setLastSolvedDate(null);
        
        progressDAO.addProgress(progress);
    }
    
    // Update total solved count
    public void updateTotalSolved(int userId) {
        progressDAO.updateTotalSolved(userId);
    }
    
    // Update accuracy
    public void updateAccuracy(int userId) {
        progressDAO.updateAccuracy(userId);
    }
    
    // Calculate and display statistics
    public void displayStatistics(int userId) {
        Progress progress = progressDAO.getProgressByUserId(userId);
        
        if (progress != null) {
            System.out.println("\n=== User Progress Statistics ===");
            System.out.println("Total Solved: " + progress.getTotalSolved());
            System.out.println("Current Streak: " + progress.getCurrentStreak() + " days");
            System.out.println("Longest Streak: " + progress.getLongestStreak() + " days");
            System.out.println("Accuracy: " + String.format("%.2f", progress.getAccuracy()) + "%");
            System.out.println("Last Solved: " + progress.getLastSolvedDate());
            System.out.println("================================\n");
        } else {
            System.out.println("No progress data found for user.");
        }
    }
    
    // Update all statistics at once
    public void updateAllStatistics(int userId) {
        updateTotalSolved(userId);
        updateAccuracy(userId);
        System.out.println("✓ All statistics updated!");
    }
}
