package com.cptracker.ui;

import com.cptracker.model.Problem;
import com.cptracker.model.Progress;
import com.cptracker.service.ProblemService;
import com.cptracker.service.ProgressService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Swing GUI for CP Progress Tracker
 * Professional tabbed interface with clean design
 */
public class CPTrackerGUI extends JFrame {
    
    // Services
    private ProblemService problemService;
    private ProgressService progressService;
    private int currentUserId = 1; // Default user
    
    // Tab 1: Add Problem Components
    private JTextField titleField;
    private JComboBox<String> platformCombo;
    private JComboBox<String> difficultyCombo;
    private JComboBox<String> statusCombo;
    private JTextArea notesArea;
    
    // Tab 2: View Problems Components
    private JTable problemsTable;
    private DefaultTableModel tableModel;
    private List<Problem> currentProblems; // Store full problem objects for notes access
    
    // Tab 3: Progress Components
    private JLabel totalSolvedLabel;
    private JLabel accuracyLabel;
    private JLabel currentStreakLabel;
    private JLabel longestStreakLabel;
    private JLabel easyCountLabel;
    private JLabel mediumCountLabel;
    private JLabel hardCountLabel;
    
    public CPTrackerGUI() {
        // Initialize services
        problemService = new ProblemService();
        progressService = new ProgressService();
        
        // Setup frame
        setTitle("CP Progress Tracker");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Create UI components
        createTopPanel();
        createTabbedPane();
        
        setVisible(true);
    }
    
    // Create top panel with title
    private void createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(41, 128, 185));
        topPanel.setPreferredSize(new Dimension(900, 80));
        
        JLabel titleLabel = new JLabel("Competitive Programming Progress Tracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);
    }
    
    // Create tabbed pane with 3 tabs
    private void createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Add tabs
        tabbedPane.addTab("Add Problem", createAddProblemTab());
        tabbedPane.addTab("View Problems", createViewProblemsTab());
        tabbedPane.addTab("Progress", createProgressTab());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    // TAB 1: Add Problem
    private JPanel createAddProblemTab() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Problem Title:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        titleField = new JTextField(30);
        titleField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(titleField, gbc);
        
        // Platform
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panel.add(createLabel("Platform:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        platformCombo = new JComboBox<>(new String[]{"LeetCode", "Codeforces", "HackerRank", "CodeChef", "AtCoder"});
        platformCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(platformCombo, gbc);
        
        // Difficulty
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panel.add(createLabel("Difficulty:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        difficultyCombo = new JComboBox<>(new String[]{"EASY", "MEDIUM", "HARD"});
        difficultyCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(difficultyCombo, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panel.add(createLabel("Status:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        statusCombo = new JComboBox<>(new String[]{"TODO", "ATTEMPTED", "SOLVED", "REVIEW"});
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(statusCombo, gbc);
        
        // Notes
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        panel.add(createLabel("Notes:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        notesArea = new JTextArea(5, 30);
        notesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        JScrollPane notesScroll = new JScrollPane(notesArea);
        panel.add(notesScroll, gbc);
        
        // Submit Button
        gbc.gridx = 1; gbc.gridy = 5; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JButton submitButton = createStyledButton("Add Problem", new Color(46, 204, 113));
        submitButton.addActionListener(e -> addProblem());
        panel.add(submitButton, gbc);
        
        return panel;
    }
    
    // TAB 2: View Problems
    private JPanel createViewProblemsTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Table
        String[] columns = {"ID", "Title", "Platform", "Difficulty", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        problemsTable = new JTable(tableModel);
        problemsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        problemsTable.setRowHeight(30);
        problemsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        problemsTable.getTableHeader().setBackground(new Color(52, 152, 219));
        problemsTable.getTableHeader().setForeground(Color.WHITE);
        problemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        problemsTable.setSelectionBackground(new Color(52, 152, 219));
        problemsTable.setSelectionForeground(Color.WHITE);
        problemsTable.setShowGrid(true);
        problemsTable.setGridColor(new Color(200, 200, 200));
        
        // Center align specific columns
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        problemsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID
        problemsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Difficulty
        problemsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Status
        
        // Set column widths
        problemsTable.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        problemsTable.getColumnModel().getColumn(1).setPreferredWidth(250); // Title
        problemsTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Platform
        problemsTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Difficulty
        problemsTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Status
        
        JScrollPane scrollPane = new JScrollPane(problemsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        JButton refreshButton = createActionButton("Refresh", new Color(52, 152, 219));
        refreshButton.addActionListener(e -> loadProblems());
        
        JButton viewNotesButton = createActionButton("View Notes", new Color(155, 89, 182));
        viewNotesButton.addActionListener(e -> viewNotes());
        
        JButton markSolvedButton = createActionButton("Mark as Solved", new Color(46, 204, 113));
        markSolvedButton.addActionListener(e -> markProblemAsSolved());
        
        // NEW: Mark for Review button
        JButton markReviewButton = createActionButton("Mark for Review", new Color(243, 156, 18));
        markReviewButton.addActionListener(e -> markProblemForReview());
        
        // NEW: View Review Problems button
        JButton viewReviewButton = createActionButton("View Review", new Color(142, 68, 173));
        viewReviewButton.addActionListener(e -> viewReviewProblems());
        
        // NEW: Remove from Review button
        JButton removeReviewButton = createActionButton("Remove Review", new Color(52, 73, 94));
        removeReviewButton.addActionListener(e -> removeFromReview());
        
        JButton deleteButton = createActionButton("Delete", new Color(231, 76, 60));
        deleteButton.addActionListener(e -> deleteProblem());
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(viewNotesButton);
        buttonPanel.add(markSolvedButton);
        buttonPanel.add(markReviewButton);  // NEW
        buttonPanel.add(viewReviewButton);   // NEW
        buttonPanel.add(removeReviewButton); // NEW
        buttonPanel.add(deleteButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Load initial data
        loadProblems();
        
        return panel;
    }
    
    // TAB 3: Progress
    private JPanel createProgressTab() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        // Center panel with all statistics
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        // Section 1: Overall Statistics
        JPanel overallStatsPanel = createStatsSection("Overall Statistics");
        
        // Total Solved
        JPanel totalSolvedPanel = createStatRow("Total Solved:", "0", new Color(52, 152, 219));
        totalSolvedLabel = (JLabel) ((JPanel) totalSolvedPanel.getComponent(1)).getComponent(0);
        overallStatsPanel.add(totalSolvedPanel);
        
        // Accuracy
        JPanel accuracyPanel = createStatRow("Accuracy (Solved / Total):", "0.00%", new Color(46, 204, 113));
        accuracyLabel = (JLabel) ((JPanel) accuracyPanel.getComponent(1)).getComponent(0);
        overallStatsPanel.add(accuracyPanel);
        
        centerPanel.add(overallStatsPanel);
        centerPanel.add(Box.createVerticalStrut(30));
        
        // Section 2: Streak Information
        JPanel streakPanel = createStatsSection("Streak Information");
        
        // Current Streak
        JPanel currentStreakPanel = createStatRow("Current Streak:", "0 days", new Color(230, 126, 34));
        currentStreakLabel = (JLabel) ((JPanel) currentStreakPanel.getComponent(1)).getComponent(0);
        streakPanel.add(currentStreakPanel);
        
        // Longest Streak
        JPanel longestStreakPanel = createStatRow("Longest Streak:", "0 days", new Color(230, 126, 34));
        longestStreakLabel = (JLabel) ((JPanel) longestStreakPanel.getComponent(1)).getComponent(0);
        streakPanel.add(longestStreakPanel);
        
        centerPanel.add(streakPanel);
        centerPanel.add(Box.createVerticalStrut(30));
        
        // Section 3: Difficulty Breakdown
        JPanel difficultyPanel = createStatsSection("Difficulty Breakdown");
        
        // EASY
        JPanel easyPanel = createStatRow("EASY:", "0", new Color(46, 204, 113));
        easyCountLabel = (JLabel) ((JPanel) easyPanel.getComponent(1)).getComponent(0);
        difficultyPanel.add(easyPanel);
        
        // MEDIUM
        JPanel mediumPanel = createStatRow("MEDIUM:", "0", new Color(241, 196, 15));
        mediumCountLabel = (JLabel) ((JPanel) mediumPanel.getComponent(1)).getComponent(0);
        difficultyPanel.add(mediumPanel);
        
        // HARD
        JPanel hardPanel = createStatRow("HARD:", "0", new Color(231, 76, 60));
        hardCountLabel = (JLabel) ((JPanel) hardPanel.getComponent(1)).getComponent(0);
        difficultyPanel.add(hardPanel);
        
        centerPanel.add(difficultyPanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Bottom: Refresh Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton refreshButton = createActionButton("Refresh Statistics", new Color(52, 152, 219));
        refreshButton.setPreferredSize(new Dimension(200, 45));
        refreshButton.setFont(new Font("Arial", Font.BOLD, 15));
        refreshButton.addActionListener(e -> {
            loadProgress();
            JOptionPane.showMessageDialog(this, "Statistics refreshed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        
        buttonPanel.add(refreshButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Load initial data
        loadProgress();
        
        return mainPanel;
    }
    
    // Helper: Create section panel with title
    private JPanel createStatsSection(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.setBackground(Color.WHITE);
        
        // Section title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15));
        
        return panel;
    }
    
    // Helper: Create stat row with label and value
    private JPanel createStatRow(String labelText, String valueText, Color valueColor) {
        JPanel rowPanel = new JPanel(new BorderLayout(20, 0));
        rowPanel.setMaximumSize(new Dimension(600, 50));
        rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rowPanel.setBackground(Color.WHITE);
        rowPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        
        // Label
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(52, 73, 94));
        
        // Value panel (to hold the value label)
        JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        valuePanel.setBackground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(valueText);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 22));
        valueLabel.setForeground(valueColor);
        
        valuePanel.add(valueLabel);
        
        rowPanel.add(label, BorderLayout.WEST);
        rowPanel.add(valuePanel, BorderLayout.EAST);
        
        return rowPanel;
    }
    
    // Add Problem Action
    private void addProblem() {
        String title = titleField.getText().trim();
        
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a problem title!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String platform = (String) platformCombo.getSelectedItem();
        String difficulty = (String) difficultyCombo.getSelectedItem();
        String status = (String) statusCombo.getSelectedItem();
        String notes = notesArea.getText().trim();
        
        LocalDate solvedDate = status.equals("SOLVED") ? LocalDate.now() : null;
        
        Problem problem = new Problem(
            currentUserId,
            title,
            platform,
            difficulty,
            status,
            solvedDate,
            1,
            notes
        );
        
        problemService.addProblem(problem);
        
        if (status.equals("SOLVED")) {
            progressService.updateAllStatistics(currentUserId);
        }
        
        JOptionPane.showMessageDialog(this, "Problem added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear form
        titleField.setText("");
        notesArea.setText("");
        platformCombo.setSelectedIndex(0);
        difficultyCombo.setSelectedIndex(0);
        statusCombo.setSelectedIndex(0);
    }
    
    // Load Problems into Table
    private void loadProblems() {
        tableModel.setRowCount(0);
        currentProblems = problemService.getAllProblems(currentUserId);
        
        for (Problem p : currentProblems) {
            tableModel.addRow(new Object[]{
                p.getProblemId(),
                p.getTitle(),
                p.getPlatform(),
                p.getDifficulty(),
                p.getStatus()
            });
        }
    }
    
    // View Notes Action
    private void viewNotes() {
        int selectedRow = problemsTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a problem to view notes!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Problem selectedProblem = currentProblems.get(selectedRow);
        String notes = selectedProblem.getNotes();
        
        if (notes == null || notes.trim().isEmpty()) {
            notes = "No notes available for this problem.";
        }
        
        JTextArea textArea = new JTextArea(notes);
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setRows(12);
        textArea.setColumns(50);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 350));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JOptionPane.showMessageDialog(this, 
            scrollPane, 
            "Notes: " + selectedProblem.getTitle(), 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Mark Problem as Solved
    private void markProblemAsSolved() {
        int selectedRow = problemsTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a problem!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int problemId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 4);
        
        if (currentStatus.equals("SOLVED")) {
            JOptionPane.showMessageDialog(this, "Problem is already solved!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        problemService.markProblemAsSolved(problemId, currentUserId);
        
        JOptionPane.showMessageDialog(this, "Problem marked as solved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        loadProblems();
        loadProgress();
    }
    
    // Delete Problem
    private void deleteProblem() {
        int selectedRow = problemsTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a problem!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int problemId = (int) tableModel.getValueAt(selectedRow, 0);
        String title = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete:\n" + title + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            problemService.deleteProblem(problemId);
            progressService.updateAllStatistics(currentUserId);
            
            JOptionPane.showMessageDialog(this, "Problem deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            loadProblems();
            loadProgress();
        }
    }
    
    // NEW: Mark Problem for Review
    /**
     * Marks selected problem for review
     * Updates status to REVIEW using existing updateProblem method
     */
    private void markProblemForReview() {
        int selectedRow = problemsTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a problem!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int problemId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 4);
        
        if (currentStatus.equals("REVIEW")) {
            JOptionPane.showMessageDialog(this, "Problem is already marked for review!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Get the problem and update its status
        Problem problem = currentProblems.get(selectedRow);
        problem.setStatus("REVIEW");
        problemService.updateProblem(problem);
        
        JOptionPane.showMessageDialog(this, "Problem marked for review!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Refresh table to show updated status
        loadProblems();
    }
    
    // NEW: View Review Problems
    /**
     * Filters and displays only problems marked for REVIEW
     * Uses existing service method to filter by status
     */
    private void viewReviewProblems() {
        tableModel.setRowCount(0);
        
        // Get only REVIEW problems using existing filter method
        List<Problem> reviewProblems = problemService.getProblemsByStatus(currentUserId, "REVIEW");
        currentProblems = reviewProblems;
        
        if (reviewProblems.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No problems marked for review.\nMark problems for review to see them here!", 
                "No Review Problems", 
                JOptionPane.INFORMATION_MESSAGE);
            // Reload all problems
            loadProblems();
            return;
        }
        
        // Display review problems in table
        for (Problem p : reviewProblems) {
            tableModel.addRow(new Object[]{
                p.getProblemId(),
                p.getTitle(),
                p.getPlatform(),
                p.getDifficulty(),
                p.getStatus()
            });
        }
        
        JOptionPane.showMessageDialog(this, 
            "Showing " + reviewProblems.size() + " problem(s) marked for review.\nClick 'Refresh' to see all problems.", 
            "Review Filter Active", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // NEW: Remove from Review
    /**
     * Removes REVIEW status from selected problem
     * Changes status back to ATTEMPTED (can be changed to TODO if preferred)
     */
    private void removeFromReview() {
        int selectedRow = problemsTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a problem!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String currentStatus = (String) tableModel.getValueAt(selectedRow, 4);
        
        if (!currentStatus.equals("REVIEW")) {
            JOptionPane.showMessageDialog(this, "Selected problem is not marked for review!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Ask user what status to change to
        String[] options = {"ATTEMPTED", "TODO", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this,
            "Change status to:",
            "Remove from Review",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) {
            return; // User cancelled
        }
        
        String newStatus = options[choice];
        
        // Get the problem and update its status
        Problem problem = currentProblems.get(selectedRow);
        problem.setStatus(newStatus);
        problemService.updateProblem(problem);
        
        JOptionPane.showMessageDialog(this, 
            "Problem removed from review!\nStatus changed to: " + newStatus, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Refresh table to show updated status
        loadProblems();
    }
    
    // Load Progress Statistics
    private void loadProgress() {
        Progress progress = progressService.getProgress(currentUserId);
        
        if (progress != null) {
            // Update overall stats
            totalSolvedLabel.setText(String.valueOf(progress.getTotalSolved()));
            
            // Calculate and display accuracy with formula
            int totalProblems = problemService.getAllProblems(currentUserId).size();
            int solvedProblems = progress.getTotalSolved();
            String accuracyText = String.format("%.2f%% (%d / %d)", 
                progress.getAccuracy(), solvedProblems, totalProblems);
            accuracyLabel.setText(accuracyText);
            
            // Update streak info
            currentStreakLabel.setText(progress.getCurrentStreak() + " days");
            longestStreakLabel.setText(progress.getLongestStreak() + " days");
        }
        
        // Load difficulty breakdown
        List<Problem> easyProblems = problemService.getProblemsByDifficulty(currentUserId, "EASY");
        List<Problem> mediumProblems = problemService.getProblemsByDifficulty(currentUserId, "MEDIUM");
        List<Problem> hardProblems = problemService.getProblemsByDifficulty(currentUserId, "HARD");
        
        easyCountLabel.setText(String.valueOf(easyProblems.size()));
        mediumCountLabel.setText(String.valueOf(mediumProblems.size()));
        hardCountLabel.setText(String.valueOf(hardProblems.size()));
    }
    
    // Helper: Create styled label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
    
    // Helper: Create value label
    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(41, 128, 185));
        return label;
    }
    
    // Helper: Create styled button (for forms)
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(160, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    // Helper: Create action button (for table actions)
    private JButton createActionButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(140, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Color originalColor = bgColor;
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
        
        return button;
    }
    
    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CPTrackerGUI());
    }
}
