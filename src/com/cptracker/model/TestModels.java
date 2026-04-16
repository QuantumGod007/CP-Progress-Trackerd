package com.cptracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Test class to demonstrate Model classes usage
 */
public class TestModels {
    
    public static void main(String[] args) {
        System.out.println("=== Testing Model Classes ===\n");
        
        // Test User model
        System.out.println("1. Testing User Model:");
        User user = new User(1, "john_doe", "john@example.com", LocalDateTime.now());
        System.out.println(user);
        System.out.println("Username: " + user.getUsername());
        System.out.println();
        
        // Test Problem model
        System.out.println("2. Testing Problem Model:");
        Problem problem = new Problem(
            1, 1, "Two Sum", "LeetCode", "EASY", "SOLVED",
            LocalDate.now(), 1, "Used HashMap approach", LocalDateTime.now()
        );
        System.out.println(problem);
        System.out.println("Title: " + problem.getTitle());
        System.out.println("Difficulty: " + problem.getDifficulty());
        System.out.println();
        
        // Test Progress model
        System.out.println("3. Testing Progress Model:");
        Progress progress = new Progress(
            1, 1, 10, 5, 7, 85.5, LocalDate.now(), LocalDateTime.now()
        );
        System.out.println(progress);
        System.out.println("Total Solved: " + progress.getTotalSolved());
        System.out.println("Accuracy: " + progress.getAccuracy() + "%");
        System.out.println();
        
        // Test setters
        System.out.println("4. Testing Setters:");
        user.setEmail("newemail@example.com");
        problem.setStatus("ATTEMPTED");
        progress.setCurrentStreak(6);
        
        System.out.println("Updated Email: " + user.getEmail());
        System.out.println("Updated Status: " + problem.getStatus());
        System.out.println("Updated Streak: " + progress.getCurrentStreak());
        
        System.out.println("\n✓ All model classes working correctly!");
    }
}
