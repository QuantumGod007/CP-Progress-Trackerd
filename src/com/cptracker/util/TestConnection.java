package com.cptracker.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Test class to verify database connection
 */
public class TestConnection {
    
    public static void main(String[] args) {
        System.out.println("Testing Database Connection...\n");
        
        // Method 1: Using testConnection()
        DBConnection.testConnection();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Method 2: Manual connection test
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            System.out.println("✓ Manual connection test successful!");
            System.out.println("✓ Connection object: " + conn);
            
        } catch (SQLException e) {
            System.err.println("✗ Manual connection test failed!");
            e.printStackTrace();
            
        } finally {
            // Always close connection
            DBConnection.closeConnection(conn);
        }
    }
}
