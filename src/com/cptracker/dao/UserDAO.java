package com.cptracker.dao;

import com.cptracker.model.User;
import java.util.List;

/**
 * UserDAO Interface
 * Defines CRUD operations for User entity
 */
public interface UserDAO {
    
    // Create
    void addUser(User user);
    
    // Read
    User getUserById(int userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    
    // Update
    void updateUser(User user);
    
    // Delete
    void deleteUser(int userId);
}
