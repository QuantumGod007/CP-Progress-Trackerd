package com.cptracker.dao.impl;

import com.cptracker.dao.ProgressDAO;
import com.cptracker.model.Progress;
import com.cptracker.util.DBConnection;

import java.sql.*;

/**
 * ProgressDAO Implementation using JDBC
 */
public class ProgressDAOImpl implements ProgressDAO {
    
    // Create Progress
    @Override
    public void addProgress(Progress progress) {
        String sql = "INSERT INTO progress (user_id, total_solved, current_streak, longest_streak, accuracy, last_solved_date) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, progress.getUserId());
            pstmt.setInt(2, progress.getTotalSolved());
            pstmt.setInt(3, progress.getCurrentStreak());
            pstmt.setInt(4, progress.getLongestStreak());
            pstmt.setDouble(5, progress.getAccuracy());
            pstmt.setDate(6, progress.getLastSolvedDate() != null ? Date.valueOf(progress.getLastSolvedDate()) : null);
            
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✓ Progress created successfully!");
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error creating progress: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Get Progress by User ID
    @Override
    public Progress getProgressByUserId(int userId) {
        String sql = "SELECT * FROM progress WHERE user_id = ?";
        Progress progress = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                progress = new Progress();
                progress.setProgressId(rs.getInt("progress_id"));
                progress.setUserId(rs.getInt("user_id"));
                progress.setTotalSolved(rs.getInt("total_solved"));
                progress.setCurrentStreak(rs.getInt("current_streak"));
                progress.setLongestStreak(rs.getInt("longest_streak"));
                progress.setAccuracy(rs.getDouble("accuracy"));
                
                Date lastSolvedDate = rs.getDate("last_solved_date");
                if (lastSolvedDate != null) {
                    progress.setLastSolvedDate(lastSolvedDate.toLocalDate());
                }
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error fetching progress: " + e.getMessage());
            e.printStackTrace();
        }
        
        return progress;
    }
    
    // Update Progress
    @Override
    public void updateProgress(Progress progress) {
        String sql = "UPDATE progress SET total_solved = ?, current_streak = ?, longest_streak = ?, accuracy = ?, last_solved_date = ? WHERE user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, progress.getTotalSolved());
            pstmt.setInt(2, progress.getCurrentStreak());
            pstmt.setInt(3, progress.getLongestStreak());
            pstmt.setDouble(4, progress.getAccuracy());
            pstmt.setDate(5, progress.getLastSolvedDate() != null ? Date.valueOf(progress.getLastSolvedDate()) : null);
            pstmt.setInt(6, progress.getUserId());
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✓ Progress updated successfully!");
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error updating progress: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Update Total Solved Count
    @Override
    public void updateTotalSolved(int userId) {
        String sql = "UPDATE progress SET total_solved = (SELECT COUNT(*) FROM problems WHERE user_id = ? AND status = 'SOLVED') WHERE user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("✗ Error updating total solved: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Update Accuracy
    @Override
    public void updateAccuracy(int userId) {
        String sql = "UPDATE progress SET accuracy = (SELECT (COUNT(CASE WHEN status = 'SOLVED' THEN 1 END) * 100.0 / COUNT(*)) FROM problems WHERE user_id = ?) WHERE user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("✗ Error updating accuracy: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Unimplemented methods
    @Override
    public void updateStreak(int userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void deleteProgress(int userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
