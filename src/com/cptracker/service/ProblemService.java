package com.cptracker.service;

import com.cptracker.dao.ProblemDAO;
import com.cptracker.dao.impl.ProblemDAOImpl;
import com.cptracker.model.Problem;

import java.time.LocalDate;
import java.util.List;

/**
 * ProblemService - Business Logic Layer
 * Handles problem-related operations
 */
public class ProblemService {
    
    private ProblemDAO problemDAO;
    private ProgressService progressService;
    
    public ProblemService() {
        this.problemDAO = new ProblemDAOImpl();
        this.progressService = new ProgressService();
    }
    
    // Add new problem
    public void addProblem(Problem problem) {
        problemDAO.addProblem(problem);
    }
    
    // Get all problems for a user
    public List<Problem> getAllProblems(int userId) {
        return problemDAO.getAllProblems(userId);
    }
    
    // Update problem
    public void updateProblem(Problem problem) {
        problemDAO.updateProblem(problem);
    }
    
    // Delete problem
    public void deleteProblem(int problemId) {
        problemDAO.deleteProblem(problemId);
    }
    
    // Mark problem as solved and update progress
    public void markProblemAsSolved(int problemId, int userId) {
        // Get the problem first
        List<Problem> problems = problemDAO.getAllProblems(userId);
        Problem problem = null;
        
        for (Problem p : problems) {
            if (p.getProblemId() == problemId) {
                problem = p;
                break;
            }
        }
        
        if (problem != null) {
            // Update problem status
            problem.setStatus("SOLVED");
            problem.setSolvedDate(LocalDate.now());
            problemDAO.updateProblem(problem);
            
            // Update progress statistics
            progressService.updateTotalSolved(userId);
            progressService.updateAccuracy(userId);
            
            System.out.println("✓ Problem marked as solved and progress updated!");
        } else {
            System.out.println("✗ Problem not found!");
        }
    }
    
    // Get problems by status
    public List<Problem> getProblemsByStatus(int userId, String status) {
        List<Problem> allProblems = problemDAO.getAllProblems(userId);
        List<Problem> filtered = new java.util.ArrayList<>();
        
        for (Problem p : allProblems) {
            if (p.getStatus().equals(status)) {
                filtered.add(p);
            }
        }
        
        return filtered;
    }
    
    // Get problems by difficulty
    public List<Problem> getProblemsByDifficulty(int userId, String difficulty) {
        List<Problem> allProblems = problemDAO.getAllProblems(userId);
        List<Problem> filtered = new java.util.ArrayList<>();
        
        for (Problem p : allProblems) {
            if (p.getDifficulty().equals(difficulty)) {
                filtered.add(p);
            }
        }
        
        return filtered;
    }
    
    // Get count of solved problems
    public int getSolvedCount(int userId) {
        List<Problem> solvedProblems = getProblemsByStatus(userId, "SOLVED");
        return solvedProblems.size();
    }
}
