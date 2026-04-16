-- ============================================
-- Useful Queries for CP Progress Tracker
-- ============================================

USE cp_tracker;

-- ============================================
-- Basic Queries
-- ============================================

-- View all users
SELECT * FROM users;

-- View all problems for a specific user
SELECT * FROM problems WHERE user_id = 1;

-- View user progress
SELECT u.username, p.total_solved, p.current_streak, p.accuracy
FROM users u
JOIN progress p ON u.user_id = p.user_id;

-- ============================================
-- Filter Queries
-- ============================================

-- Get all SOLVED problems
SELECT title, platform, difficulty, solved_date
FROM problems
WHERE status = 'SOLVED'
ORDER BY solved_date DESC;

-- Get problems by difficulty
SELECT title, platform, status
FROM problems
WHERE difficulty = 'HARD';

-- Get problems by platform
SELECT title, difficulty, status
FROM problems
WHERE platform = 'LeetCode';

-- ============================================
-- Statistics Queries
-- ============================================

-- Count problems by status for a user
SELECT status, COUNT(*) as count
FROM problems
WHERE user_id = 1
GROUP BY status;

-- Count problems by difficulty for a user
SELECT difficulty, COUNT(*) as count
FROM problems
WHERE user_id = 1
GROUP BY difficulty;

-- Get user's solved problems count by platform
SELECT platform, COUNT(*) as solved_count
FROM problems
WHERE user_id = 1 AND status = 'SOLVED'
GROUP BY platform;

-- ============================================
-- Advanced Queries
-- ============================================

-- Get user dashboard (complete overview)
SELECT 
    u.username,
    p.total_solved,
    p.current_streak,
    p.longest_streak,
    p.accuracy,
    p.last_solved_date,
    (SELECT COUNT(*) FROM problems WHERE user_id = u.user_id AND status = 'SOLVED') as verified_solved,
    (SELECT COUNT(*) FROM problems WHERE user_id = u.user_id AND status = 'ATTEMPTED') as attempted,
    (SELECT COUNT(*) FROM problems WHERE user_id = u.user_id AND status = 'TODO') as todo
FROM users u
LEFT JOIN progress p ON u.user_id = p.user_id
WHERE u.user_id = 1;

-- Get recent activity (last 7 days)
SELECT title, platform, difficulty, solved_date
FROM problems
WHERE user_id = 1 
  AND status = 'SOLVED'
  AND solved_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
ORDER BY solved_date DESC;

-- Get problems that need retry (attempted but not solved)
SELECT title, platform, difficulty, attempts
FROM problems
WHERE user_id = 1 AND status = 'ATTEMPTED'
ORDER BY attempts DESC;

-- ============================================
-- Update Queries (for testing)
-- ============================================

-- Mark a problem as solved
UPDATE problems
SET status = 'SOLVED', solved_date = CURDATE()
WHERE problem_id = 3;

-- Increment attempts
UPDATE problems
SET attempts = attempts + 1
WHERE problem_id = 3;

-- Update progress stats
UPDATE progress
SET total_solved = total_solved + 1,
    current_streak = current_streak + 1,
    last_solved_date = CURDATE()
WHERE user_id = 1;

-- ============================================
-- Delete Queries (use with caution)
-- ============================================

-- Delete a specific problem
DELETE FROM problems WHERE problem_id = 10;

-- Delete all TODO problems for a user
DELETE FROM problems WHERE user_id = 1 AND status = 'TODO';

-- ============================================
-- Cleanup (Reset Database)
-- ============================================

-- Clear all data but keep structure
-- TRUNCATE TABLE problems;
-- TRUNCATE TABLE progress;
-- TRUNCATE TABLE users;
