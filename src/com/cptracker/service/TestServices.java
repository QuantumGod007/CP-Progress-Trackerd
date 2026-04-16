package com.cptracker.service;

import com.cptracker.model.Problem;
import java.time.LocalDate;
import java.util.List;

/**
 * Test class for Service Layer
 */
public class TestServices {
    
    public static void main(String[] args) {
        System.out.println("=== Testing Service Layer ===\n");
        
        ProblemService problemService = new ProblemService();
        ProgressService progressService = new ProgressService();
        
        int userId = 1; // john_doe from sample data
        
        // Test 1: Display current progress
        System.out.println("1. Current Progress:");
        progressService.displayStatistics(userId);
        
        // Test 2: Add a new problem
        System.out.println("2. Adding new problem:");
        Problem newProblem = new Problem(
            userId,
            "Reverse Linked List",
            "LeetCode",
            "EASY",
            "TODO",
            null,
            1,
            "Need to practice"
        );
        problemService.addProblem(newProblem);
        System.out.println();
        
        // Test 3: Get all problems
        System.out.println("3. All problems for user:");
        List<Problem> allProblems = problemService.getAllProblems(userId);
        System.out.println("Total problems: " + allProblems.size());
        for (Problem p : allProblems) {
            System.out.println("  - " + p.getTitle() + " [" + p.getStatus() + "]");
        }
        System.out.println();
        
        // Test 4: Mark problem as solved
        System.out.println("4. Marking problem as solved:");
        if (!allProblems.isEmpty()) {
            Problem toSolve = allProblems.get(0);
            if (!toSolve.getStatus().equals("SOLVED")) {
                problemService.markProblemAsSolved(toSolve.getProblemId(), userId);
            }
        }
        System.out.println();
        
        // Test 5: Get solved problems
        System.out.println("5. Solved problems:");
        List<Problem> solvedProblems = problemService.getProblemsByStatus(userId, "SOLVED");
        System.out.println("Total solved: " + solvedProblems.size());
        for (Problem p : solvedProblems) {
            System.out.println("  - " + p.getTitle() + " [" + p.getDifficulty() + "]");
        }
        System.out.println();
        
        // Test 6: Get problems by difficulty
        System.out.println("6. EASY problems:");
        List<Problem> easyProblems = problemService.getProblemsByDifficulty(userId, "EASY");
        System.out.println("Total EASY problems: " + easyProblems.size());
        System.out.println();
        
        // Test 7: Update all statistics
        System.out.println("7. Updating all statistics:");
        progressService.updateAllStatistics(userId);
        System.out.println();
        
        // Test 8: Display updated progress
        System.out.println("8. Updated Progress:");
        progressService.displayStatistics(userId);
        
        System.out.println("✓ All service tests completed!");
    }
}
