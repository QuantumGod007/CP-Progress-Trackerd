-- ============================================
-- Competitive Programming Progress Tracker
-- Database Schema
-- ============================================

-- Drop existing database if exists (use with caution)
DROP DATABASE IF EXISTS cp_tracker;

-- Create database
CREATE DATABASE cp_tracker;

-- Use the database
USE cp_tracker;

-- ============================================
-- Table 1: users
-- ============================================
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- Table 2: problems
-- ============================================
CREATE TABLE problems (
    problem_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    platform VARCHAR(50) NOT NULL,
    difficulty ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL,
    status ENUM('SOLVED', 'ATTEMPTED', 'TODO') NOT NULL,
    solved_date DATE,
    attempts INT DEFAULT 1,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ============================================
-- Table 3: progress
-- ============================================
CREATE TABLE progress (
    progress_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE NOT NULL,
    total_solved INT DEFAULT 0,
    current_streak INT DEFAULT 0,
    longest_streak INT DEFAULT 0,
    accuracy DECIMAL(5,2) DEFAULT 0.00,
    last_solved_date DATE,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ============================================
-- Indexes for better query performance
-- ============================================
CREATE INDEX idx_user_id ON problems(user_id);
CREATE INDEX idx_status ON problems(status);
CREATE INDEX idx_difficulty ON problems(difficulty);
CREATE INDEX idx_platform ON problems(platform);
CREATE INDEX idx_solved_date ON problems(solved_date);
