package com.cptracker.dao;

import com.cptracker.model.Problem;
import java.util.List;

/**
 * ProblemDAO Interface
 * Defines CRUD operations for Problem entity
 */
public interface ProblemDAO {
    
    // Create
    void addProblem(Problem problem);
    
    // Read
    Problem getProblemById(int problemId);
    List<Problem> getAllProblems(int userId);
    List<Problem> getProblemsByStatus(int userId, String status);
    List<Problem> getProblemsByDifficulty(int userId, String difficulty);
    List<Problem> getProblemsByPlatform(int userId, String platform);
    
    // Update
    void updateProblem(Problem problem);
    void markAsSolved(int problemId);
    void incrementAttempts(int problemId);
    
    // Delete
    void deleteProblem(int problemId);
}
