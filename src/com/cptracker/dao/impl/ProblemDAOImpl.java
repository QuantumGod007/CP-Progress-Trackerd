package com.cptracker.dao.impl;

import com.cptracker.dao.ProblemDAO;
import com.cptracker.model.Problem;
import com.cptracker.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ProblemDAO Implementation using JDBC
 */
public class ProblemDAOImpl implements ProblemDAO {
    
    // 1. Add Problem
    @Override
    public void addProblem(Problem problem) {
        String sql = "INSERT INTO problems (user_id, title, platform, difficulty, status, solved_date, attempts, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, problem.getUserId());
            pstmt.setString(2, problem.getTitle());
            pstmt.setString(3, problem.getPlatform());
            pstmt.setString(4, problem.getDifficulty());
            pstmt.setString(5, problem.getStatus());
            pstmt.setDate(6, problem.getSolvedDate() != null ? Date.valueOf(problem.getSolvedDate()) : null);
            pstmt.setInt(7, problem.getAttempts());
            pstmt.setString(8, problem.getNotes());
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✓ Problem added successfully!");
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error adding problem: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // 2. Get All Problems by User ID
    @Override
    public List<Problem> getAllProblems(int userId) {
        List<Problem> problems = new ArrayList<>();
        String sql = "SELECT * FROM problems WHERE user_id = ? ORDER BY problem_id DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Problem problem = new Problem();
                problem.setProblemId(rs.getInt("problem_id"));
                problem.setUserId(rs.getInt("user_id"));
                problem.setTitle(rs.getString("title"));
                problem.setPlatform(rs.getString("platform"));
                problem.setDifficulty(rs.getString("difficulty"));
                problem.setStatus(rs.getString("status"));
                
                Date solvedDate = rs.getDate("solved_date");
                if (solvedDate != null) {
                    problem.setSolvedDate(solvedDate.toLocalDate());
                }
                
                problem.setAttempts(rs.getInt("attempts"));
                problem.setNotes(rs.getString("notes"));
                
                // created_at column not in current schema
                
                problems.add(problem);
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error fetching problems: " + e.getMessage());
            e.printStackTrace();
        }
        
        return problems;
    }
    
    // 3. Update Problem
    @Override
    public void updateProblem(Problem problem) {
        String sql = "UPDATE problems SET title = ?, platform = ?, difficulty = ?, status = ?, solved_date = ?, attempts = ?, notes = ? WHERE problem_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, problem.getTitle());
            pstmt.setString(2, problem.getPlatform());
            pstmt.setString(3, problem.getDifficulty());
            pstmt.setString(4, problem.getStatus());
            pstmt.setDate(5, problem.getSolvedDate() != null ? Date.valueOf(problem.getSolvedDate()) : null);
            pstmt.setInt(6, problem.getAttempts());
            pstmt.setString(7, problem.getNotes());
            pstmt.setInt(8, problem.getProblemId());
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✓ Problem updated successfully!");
            } else {
                System.out.println("✗ Problem not found!");
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error updating problem: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // 4. Delete Problem
    @Override
    public void deleteProblem(int problemId) {
        String sql = "DELETE FROM problems WHERE problem_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, problemId);
            
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✓ Problem deleted successfully!");
            } else {
                System.out.println("✗ Problem not found!");
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error deleting problem: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Unimplemented methods (required by interface)
    @Override
    public Problem getProblemById(int problemId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Problem> getProblemsByStatus(int userId, String status) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Problem> getProblemsByDifficulty(int userId, String difficulty) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Problem> getProblemsByPlatform(int userId, String platform) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void markAsSolved(int problemId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void incrementAttempts(int problemId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
