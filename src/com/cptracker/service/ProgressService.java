package com.cptracker.service;

import com.cptracker.dao.ProgressDAO;
import com.cptracker.dao.impl.ProgressDAOImpl;
import com.cptracker.dao.ProblemDAO;
import com.cptracker.dao.impl.ProblemDAOImpl;
import com.cptracker.model.Progress;
import com.cptracker.model.Problem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ProgressService - Business Logic Layer
 * Handles progress statistics operations
 */
public class ProgressService {
    
    private ProgressDAO progressDAO;
    private ProblemDAO problemDAO;
    
    public ProgressService() {
        this.progressDAO = new ProgressDAOImpl();
        this.problemDAO = new ProblemDAOImpl();
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
        updateStreak(userId);  // Add streak calculation
        System.out.println("✓ All statistics updated!");
    }
    
    /**
     * Calculate and update streak based on solved dates
     * Streak = consecutive days with at least 1 solved problem
     */
    public void updateStreak(int userId) {
        // Step 1: Get all SOLVED problems for the user
        List<Problem> allProblems = problemDAO.getAllProblems(userId);
        List<Problem> solvedProblems = allProblems.stream()
            .filter(p -> "SOLVED".equals(p.getStatus()) && p.getSolvedDate() != null)
            .collect(Collectors.toList());
        
        // If no solved problems, set streak to 0
        if (solvedProblems.isEmpty()) {
            updateStreakInDatabase(userId, 0, 0, null);
            return;
        }
        
        // Step 2: Extract unique solved dates (one problem per day counts as 1 day)
        Set<LocalDate> uniqueDates = solvedProblems.stream()
            .map(Problem::getSolvedDate)
            .collect(Collectors.toSet());
        
        // Step 3: Sort dates in descending order (most recent first)
        List<LocalDate> sortedDates = uniqueDates.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        
        // Step 4: Calculate current streak
        int currentStreak = calculateCurrentStreak(sortedDates);
        
        // Step 5: Calculate longest streak (historical best)
        int longestStreak = calculateLongestStreak(sortedDates);
        
        // Step 6: Get last solved date
        LocalDate lastSolvedDate = sortedDates.get(0); // Most recent date
        
        // Step 7: Update database
        updateStreakInDatabase(userId, currentStreak, longestStreak, lastSolvedDate);
    }
    
    /**
     * Calculate current streak (consecutive days from today or most recent solve)
     * Logic:
     * - Start from most recent solved date
     * - Count backwards: if dates are consecutive, increment streak
     * - Stop when gap is found
     */
    private int calculateCurrentStreak(List<LocalDate> sortedDates) {
        if (sortedDates.isEmpty()) {
            return 0;
        }
        
        LocalDate today = LocalDate.now();
        LocalDate mostRecentSolve = sortedDates.get(0);
        
        // Check if most recent solve is today or yesterday
        // If more than 1 day gap, streak is broken
        long daysSinceLastSolve = ChronoUnit.DAYS.between(mostRecentSolve, today);
        if (daysSinceLastSolve > 1) {
            return 0; // Streak broken
        }
        
        // Count consecutive days
        int streak = 1; // Start with 1 (most recent day)
        LocalDate expectedDate = mostRecentSolve.minusDays(1);
        
        for (int i = 1; i < sortedDates.size(); i++) {
            LocalDate currentDate = sortedDates.get(i);
            
            // Check if current date matches expected consecutive date
            if (currentDate.equals(expectedDate)) {
                streak++;
                expectedDate = expectedDate.minusDays(1); // Move to next expected date
            } else if (currentDate.isBefore(expectedDate)) {
                // Gap found, streak ends
                break;
            }
            // If currentDate is after expectedDate, skip (duplicate handling)
        }
        
        return streak;
    }
    
    /**
     * Calculate longest streak in entire history
     * Logic:
     * - Sort all dates
     * - Find longest consecutive sequence
     */
    private int calculateLongestStreak(List<LocalDate> sortedDates) {
        if (sortedDates.isEmpty()) {
            return 0;
        }
        
        // Sort in ascending order for easier consecutive checking
        List<LocalDate> ascendingDates = new ArrayList<>(sortedDates);
        Collections.sort(ascendingDates);
        
        int longestStreak = 1;
        int currentStreak = 1;
        
        for (int i = 1; i < ascendingDates.size(); i++) {
            LocalDate prevDate = ascendingDates.get(i - 1);
            LocalDate currDate = ascendingDates.get(i);
            
            // Check if dates are consecutive (1 day apart)
            long daysBetween = ChronoUnit.DAYS.between(prevDate, currDate);
            
            if (daysBetween == 1) {
                currentStreak++;
                longestStreak = Math.max(longestStreak, currentStreak);
            } else if (daysBetween > 1) {
                // Gap found, reset current streak
                currentStreak = 1;
            }
            // If daysBetween == 0, same date (already handled by Set)
        }
        
        return longestStreak;
    }
    
    /**
     * Update streak values in database
     */
    private void updateStreakInDatabase(int userId, int currentStreak, int longestStreak, LocalDate lastSolvedDate) {
        Progress progress = progressDAO.getProgressByUserId(userId);
        
        if (progress != null) {
            progress.setCurrentStreak(currentStreak);
            
            // Only update longest streak if current is higher
            if (longestStreak > progress.getLongestStreak()) {
                progress.setLongestStreak(longestStreak);
            }
            
            progress.setLastSolvedDate(lastSolvedDate);
            progressDAO.updateProgress(progress);
        }
    }
}
