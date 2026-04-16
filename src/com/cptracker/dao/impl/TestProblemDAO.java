package com.cptracker.dao.impl;

import com.cptracker.model.Problem;
import java.time.LocalDate;
import java.util.List;

/**
 * Test class for ProblemDAOImpl
 */
public class TestProblemDAO {
    
    public static void main(String[] args) {
        System.out.println("=== Testing ProblemDAOImpl ===\n");
        
        ProblemDAOImpl problemDAO = new ProblemDAOImpl();
        
        // Test 1: Add Problem
        System.out.println("1. Testing addProblem():");
        Problem problem = new Problem(
            1,  // userId (john_doe from sample data)
            "Valid Parentheses",
            "LeetCode",
            "EASY",
            "SOLVED",
            LocalDate.now(),
            1,
            "Stack-based solution"
        );
        problemDAO.addProblem(problem);
        System.out.println();
        
        // Test 2: Get All Problems
        System.out.println("2. Testing getAllProblems():");
        List<Problem> problems = problemDAO.getAllProblems(1);
        System.out.println("Found " + problems.size() + " problems for user 1:");
        for (Problem p : problems) {
            System.out.println("  - " + p.getTitle() + " [" + p.getDifficulty() + "] - " + p.getStatus());
        }
        System.out.println();
        
        // Test 3: Update Problem
        System.out.println("3. Testing updateProblem():");
        if (!problems.isEmpty()) {
            Problem toUpdate = problems.get(0);
            toUpdate.setStatus("ATTEMPTED");
            toUpdate.setAttempts(2);
            toUpdate.setNotes("Need to review again");
            problemDAO.updateProblem(toUpdate);
        }
        System.out.println();
        
        // Test 4: Delete Problem
        System.out.println("4. Testing deleteProblem():");
        if (!problems.isEmpty()) {
            int lastProblemId = problems.get(problems.size() - 1).getProblemId();
            System.out.println("Deleting problem ID: " + lastProblemId);
            problemDAO.deleteProblem(lastProblemId);
        }
        System.out.println();
        
        // Verify changes
        System.out.println("5. Verifying changes:");
        List<Problem> updatedProblems = problemDAO.getAllProblems(1);
        System.out.println("Total problems now: " + updatedProblems.size());
        
        System.out.println("\n✓ All tests completed!");
    }
}
