package com.cptracker.dao;

import com.cptracker.model.Progress;

/**
 * ProgressDAO Interface
 * Defines CRUD operations for Progress entity
 */
public interface ProgressDAO {
    
    // Create
    void addProgress(Progress progress);
    
    // Read
    Progress getProgressByUserId(int userId);
    
    // Update
    void updateProgress(Progress progress);
    void updateTotalSolved(int userId);
    void updateStreak(int userId);
    void updateAccuracy(int userId);
    
    // Delete
    void deleteProgress(int userId);
}
