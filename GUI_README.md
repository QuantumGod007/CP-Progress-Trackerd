# CP Progress Tracker - Swing GUI Documentation

## 🎨 GUI Features

### Professional 3-Tab Interface

#### **TAB 1: Add Problem**
- **Form Fields:**
  - Problem Title (Text Field)
  - Platform (Dropdown: LeetCode, Codeforces, HackerRank, CodeChef, AtCoder)
  - Difficulty (Dropdown: EASY, MEDIUM, HARD)
  - Status (Dropdown: TODO, ATTEMPTED, SOLVED)
  - Notes (Text Area with scroll)
  
- **Features:**
  - Input validation (checks for empty title)
  - Auto-updates progress when marking as SOLVED
  - Form clears after successful submission
  - Success message dialog

#### **TAB 2: View Problems**
- **Table Display:**
  - Columns: ID, Title, Platform, Difficulty, Status
  - Single row selection
  - Non-editable cells
  - Scrollable view
  
- **Action Buttons:**
  - **Refresh** - Reload problems from database
  - **Mark as Solved** - Update selected problem status
  - **Delete** - Remove problem with confirmation
  
- **Features:**
  - Auto-loads data on tab open
  - Selection validation
  - Confirmation dialog for delete
  - Auto-refresh after actions

#### **TAB 3: Progress**
- **Statistics Display:**
  - Total Solved
  - Accuracy (percentage)
  - Current Streak (days)
  - Longest Streak (days)
  
- **Problem Breakdown:**
  - EASY count
  - MEDIUM count
  - HARD count
  
- **Features:**
  - Refresh button to update stats
  - Color-coded values
  - Clean layout with proper spacing

## 🎨 Design Elements

### Color Scheme
- **Header:** Blue (#2980b9)
- **Success Button:** Green (#2ecc71)
- **Primary Button:** Blue (#3498db)
- **Delete Button:** Red (#e74c3c)
- **Text:** Professional dark gray
- **Values:** Blue accent (#2980b9)

### Typography
- **Title:** Arial Bold 28px
- **Tab Labels:** Arial Plain 14px
- **Form Labels:** Arial Bold 16px
- **Values:** Arial Plain 16px
- **Buttons:** Arial Bold 14px

### Layout
- **Main Window:** 900x650px, centered
- **BorderLayout:** Top header + center tabs
- **GridBagLayout:** Forms with proper spacing
- **Padding:** 20-30px margins
- **Button Size:** 160x40px

## 🚀 How to Run

### Method 1: Using Script
```bash
./run-gui.sh
```

### Method 2: Direct Command
```bash
java -cp "lib/*:bin" com.cptracker.ui.CPTrackerGUI
```

### Method 3: From IDE
Run the main method in `CPTrackerGUI.java`

## 📋 Usage Guide

### Adding a Problem
1. Click "Add Problem" tab
2. Fill in all fields
3. Click "Add Problem" button
4. See success message
5. Form clears automatically

### Viewing Problems
1. Click "View Problems" tab
2. Table shows all problems
3. Click "Refresh" to reload
4. Select row for actions

### Marking as Solved
1. Select a problem in table
2. Click "Mark as Solved"
3. Confirmation message appears
4. Table and progress auto-update

### Deleting a Problem
1. Select a problem in table
2. Click "Delete"
3. Confirm in dialog
4. Problem removed from database

### Viewing Progress
1. Click "Progress" tab
2. See all statistics
3. Click "Refresh Statistics" to update

## 🔧 Technical Details

### Architecture
```
CPTrackerGUI (JFrame)
    ↓
Uses Services (No direct DB access)
    ↓
ProblemService + ProgressService
    ↓
DAO Layer
    ↓
MySQL Database
```

### Key Components
- **JFrame:** Main window
- **JTabbedPane:** 3 tabs
- **JTable:** Problem display
- **JComboBox:** Dropdowns
- **JTextArea:** Notes input
- **JOptionPane:** Dialogs

### Event Handling
- ActionListeners on all buttons
- Input validation before submission
- Error dialogs for invalid actions
- Success confirmations

## 🎯 Features Demonstrated

✅ **Swing Components**
- JFrame, JPanel, JTabbedPane
- JTable with DefaultTableModel
- JComboBox, JTextField, JTextArea
- JButton, JLabel, JScrollPane

✅ **Layout Managers**
- BorderLayout (main frame)
- GridBagLayout (forms)
- FlowLayout (button panels)

✅ **Event Handling**
- ActionListener for buttons
- Input validation
- Dialog boxes

✅ **Service Integration**
- Calls ProblemService
- Calls ProgressService
- No direct JDBC in UI

✅ **Professional Design**
- Color scheme
- Typography
- Spacing and alignment
- Responsive layout

## 📊 Comparison: Console vs GUI

| Feature | Console UI | Swing GUI |
|---------|-----------|-----------|
| Interface | Text menu | Visual tabs |
| Input | Scanner | Form fields |
| Display | Text output | Table + Labels |
| Navigation | Number selection | Tab clicks |
| Validation | Manual checks | Built-in |
| User Experience | Basic | Professional |

## 🎓 Learning Outcomes

This GUI demonstrates:
1. **Swing Basics** - Components and layouts
2. **Event-Driven Programming** - Listeners and handlers
3. **MVC Pattern** - Separation of UI and logic
4. **Service Layer** - Using existing backend
5. **Professional Design** - Clean, organized interface

## 🔄 Future Enhancements

- [ ] User login screen
- [ ] Charts and graphs (JFreeChart)
- [ ] Export to PDF/CSV
- [ ] Dark mode toggle
- [ ] Search and filter in table
- [ ] Sorting by columns
- [ ] Edit problem inline
- [ ] Drag and drop

## 📝 Notes

- Default user ID is 1 (john_doe from sample data)
- All actions auto-update related views
- Database must be running
- JDBC driver must be in lib/

---

**Enjoy your professional CP Progress Tracker GUI! 🚀**
