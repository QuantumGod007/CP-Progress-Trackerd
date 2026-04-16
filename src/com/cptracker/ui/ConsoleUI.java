package com.cptracker.ui;

import com.cptracker.model.Problem;
import com.cptracker.service.ProblemService;
import com.cptracker.service.ProgressService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI for CP Progress Tracker
 * Menu-driven interface for user interaction
 */
public class ConsoleUI {
    
    private Scanner scanner;
    private ProblemService problemService;
    private ProgressService progressService;
    private int currentUserId;
    
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.problemService = new ProblemService();
        this.progressService = new ProgressService();
        this.currentUserId = 1; // Default user (john_doe)
    }
    
    // Main menu
    public void start() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  CP PROGRESS TRACKER - Welcome!        ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProblem();
                    break;
                case 2:
                    viewAllProblems();
                    break;
                case 3:
                    markProblemAsSolved();
                    break;
                case 4:
                    deleteProblem();
                    break;
                case 5:
                    viewProgress();
                    break;
                case 6:
                    running = false;
                    System.out.println("\n✓ Thank you for using CP Progress Tracker!");
                    System.out.println("✓ Keep coding and stay consistent!\n");
                    break;
                default:
                    System.out.println("\n✗ Invalid choice! Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    // Display menu
    private void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("           MAIN MENU");
        System.out.println("========================================");
        System.out.println("1. Add Problem");
        System.out.println("2. View All Problems");
        System.out.println("3. Mark Problem as Solved");
        System.out.println("4. Delete Problem");
        System.out.println("5. View Progress");
        System.out.println("6. Exit");
        System.out.println("========================================");
    }
    
    // 1. Add Problem
    private void addProblem() {
        System.out.println("\n--- Add New Problem ---");
        
        String title = getStringInput("Enter problem title: ");
        String platform = getStringInput("Enter platform (LeetCode/Codeforces/HackerRank): ");
        String difficulty = getStringInput("Enter difficulty (EASY/MEDIUM/HARD): ").toUpperCase();
        String status = getStringInput("Enter status (TODO/ATTEMPTED/SOLVED): ").toUpperCase();
        String notes = getStringInput("Enter notes (optional): ");
        
        LocalDate solvedDate = null;
        if (status.equals("SOLVED")) {
            solvedDate = LocalDate.now();
        }
        
        Problem problem = new Problem(
            currentUserId,
            title,
            platform,
            difficulty,
            status,
            solvedDate,
            1,
            notes
        );
        
        problemService.addProblem(problem);
        
        if (status.equals("SOLVED")) {
            progressService.updateAllStatistics(currentUserId);
        }
    }
    
    // 2. View All Problems
    private void viewAllProblems() {
        System.out.println("\n--- All Problems ---");
        
        List<Problem> problems = problemService.getAllProblems(currentUserId);
        
        if (problems.isEmpty()) {
            System.out.println("No problems found. Start adding problems!");
            return;
        }
        
        System.out.println("\nTotal Problems: " + problems.size());
        System.out.println("─────────────────────────────────────────────────────────────");
        System.out.printf("%-5s %-30s %-15s %-10s %-10s%n", "ID", "Title", "Platform", "Difficulty", "Status");
        System.out.println("─────────────────────────────────────────────────────────────");
        
        for (Problem p : problems) {
            System.out.printf("%-5d %-30s %-15s %-10s %-10s%n",
                p.getProblemId(),
                truncate(p.getTitle(), 30),
                p.getPlatform(),
                p.getDifficulty(),
                p.getStatus()
            );
        }
        System.out.println("─────────────────────────────────────────────────────────────");
    }
    
    // 3. Mark Problem as Solved
    private void markProblemAsSolved() {
        System.out.println("\n--- Mark Problem as Solved ---");
        
        viewAllProblems();
        
        int problemId = getIntInput("\nEnter problem ID to mark as solved: ");
        problemService.markProblemAsSolved(problemId, currentUserId);
    }
    
    // 4. Delete Problem
    private void deleteProblem() {
        System.out.println("\n--- Delete Problem ---");
        
        viewAllProblems();
        
        int problemId = getIntInput("\nEnter problem ID to delete: ");
        String confirm = getStringInput("Are you sure? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes")) {
            problemService.deleteProblem(problemId);
            progressService.updateAllStatistics(currentUserId);
        } else {
            System.out.println("✗ Deletion cancelled.");
        }
    }
    
    // 5. View Progress
    private void viewProgress() {
        System.out.println("\n--- Your Progress ---");
        progressService.displayStatistics(currentUserId);
        
        // Additional statistics
        int solvedCount = problemService.getSolvedCount(currentUserId);
        List<Problem> easyProblems = problemService.getProblemsByDifficulty(currentUserId, "EASY");
        List<Problem> mediumProblems = problemService.getProblemsByDifficulty(currentUserId, "MEDIUM");
        List<Problem> hardProblems = problemService.getProblemsByDifficulty(currentUserId, "HARD");
        
        System.out.println("=== Problem Breakdown ===");
        System.out.println("EASY: " + easyProblems.size());
        System.out.println("MEDIUM: " + mediumProblems.size());
        System.out.println("HARD: " + hardProblems.size());
        System.out.println("=========================\n");
    }
    
    // Helper: Get string input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    // Helper: Get integer input
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }
    
    // Helper: Truncate long strings
    private String truncate(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 3) + "...";
    }
    
    // Main method
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.start();
    }
}
