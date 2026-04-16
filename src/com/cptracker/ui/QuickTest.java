package com.cptracker.ui;

import com.cptracker.model.Problem;
import com.cptracker.service.ProblemService;
import com.cptracker.service.ProgressService;

import java.time.LocalDate;
import java.util.List;

/**
 * Quick test to demonstrate Add Problem functionality
 */
public class QuickTest {
    
    public static void main(String[] args) {
        System.out.println("=== Testing Add Problem Feature ===\n");
        
        ProblemService problemService = new ProblemService();
        ProgressService progressService = new ProgressService();
        
        int userId = 1;
        
        // Simulate adding a new problem
        System.out.println("Adding new problem: 'Binary Search'");
        Problem newProblem = new Problem(
            userId,
            "Binary Search",
            "LeetCode",
            "EASY",
            "SOLVED",
            LocalDate.now(),
            1,
            "Classic binary search implementation"
        );
        
        problemService.addProblem(newProblem);
        progressService.updateAllStatistics(userId);
        
        System.out.println("\n--- All Problems After Adding ---");
        List<Problem> problems = problemService.getAllProblems(userId);
        System.out.println("Total Problems: " + problems.size());
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.printf("%-5s %-30s %-15s %-10s %-10s%n", "ID", "Title", "Platform", "Difficulty", "Status");
        System.out.println("─────────────────────────────────────────────────────────────");
        
        for (Problem p : problems) {
            System.out.printf("%-5d %-30s %-15s %-10s %-10s%n",
                p.getProblemId(),
                p.getTitle().length() > 30 ? p.getTitle().substring(0, 27) + "..." : p.getTitle(),
                p.getPlatform(),
                p.getDifficulty(),
                p.getStatus()
            );
        }
        System.out.println("─────────────────────────────────────────────────────────────");
        
        System.out.println("\n--- Updated Progress ---");
        progressService.displayStatistics(userId);
        
        System.out.println("✓ Test completed successfully!");
    }
}
