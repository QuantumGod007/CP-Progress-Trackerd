-- ============================================
-- Sample Data for CP Progress Tracker
-- ============================================

USE cp_tracker;

-- ============================================
-- Insert Users
-- ============================================
INSERT INTO users (username, email) VALUES
('john_doe', 'john@example.com'),
('alice_smith', 'alice@example.com'),
('bob_wilson', 'bob@example.com');

-- ============================================
-- Insert Problems
-- ============================================
INSERT INTO problems (user_id, title, platform, difficulty, status, solved_date, attempts, notes) VALUES
-- User 1 (john_doe) problems
(1, 'Two Sum', 'LeetCode', 'EASY', 'SOLVED', '2024-01-15', 1, 'Used HashMap approach'),
(1, 'Add Two Numbers', 'LeetCode', 'MEDIUM', 'SOLVED', '2024-01-16', 2, 'Linked list problem'),
(1, 'Longest Substring', 'LeetCode', 'MEDIUM', 'ATTEMPTED', NULL, 3, 'Need to review sliding window'),
(1, 'Median of Two Sorted Arrays', 'LeetCode', 'HARD', 'TODO', NULL, 1, 'Binary search approach'),
(1, 'Valid Parentheses', 'LeetCode', 'EASY', 'SOLVED', '2024-01-17', 1, 'Stack implementation'),

-- User 2 (alice_smith) problems
(2, 'Binary Search', 'Codeforces', 'EASY', 'SOLVED', '2024-01-10', 1, 'Classic binary search'),
(2, 'DFS Traversal', 'Codeforces', 'MEDIUM', 'SOLVED', '2024-01-12', 2, 'Graph problem'),
(2, 'Dynamic Programming', 'Codeforces', 'HARD', 'ATTEMPTED', NULL, 4, 'Still learning DP'),

-- User 3 (bob_wilson) problems
(3, 'Reverse String', 'HackerRank', 'EASY', 'SOLVED', '2024-01-20', 1, 'Two pointer approach'),
(3, 'Merge Intervals', 'HackerRank', 'MEDIUM', 'SOLVED', '2024-01-21', 1, 'Sorting required');

-- ============================================
-- Insert Progress
-- ============================================
INSERT INTO progress (user_id, total_solved, current_streak, longest_streak, accuracy, last_solved_date) VALUES
(1, 3, 3, 3, 60.00, '2024-01-17'),
(2, 2, 2, 2, 66.67, '2024-01-12'),
(3, 2, 2, 2, 100.00, '2024-01-21');
