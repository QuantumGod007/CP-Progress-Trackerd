# 🚀 Competitive Programming Progress Tracker

A full-stack Java application to track your competitive programming journey with MySQL database integration and professional Swing GUI.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)

## 📋 Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [License](#license)

## ✨ Features

### Core Functionality
- ✅ **Add Problems** - Track coding problems from multiple platforms
- ✅ **View Problems** - Display all problems in a sortable table
- ✅ **Mark as Solved** - Update problem status with one click
- ✅ **Delete Problems** - Remove problems with confirmation
- ✅ **View Notes** - Access problem-specific notes in a popup
- ✅ **Progress Statistics** - Real-time tracking of your performance

### Statistics Tracked
- 📊 **Total Solved** - Count of completed problems
- 🎯 **Accuracy** - Success rate (Solved/Total × 100)
- 🔥 **Current Streak** - Consecutive days with solved problems
- 🏆 **Longest Streak** - Best streak achieved
- 📈 **Difficulty Breakdown** - Problems by EASY/MEDIUM/HARD

### Supported Platforms
- LeetCode
- Codeforces
- HackerRank
- CodeChef
- AtCoder

## 🛠️ Tech Stack

### Backend
- **Language:** Core Java (JDK 8+)
- **Database:** MySQL 5.7+
- **JDBC:** MySQL Connector/J 8.3.0
- **Design Patterns:** DAO Pattern, MVC Pattern

### Frontend
- **GUI Framework:** Java Swing
- **Layout Managers:** BorderLayout, GridBagLayout, BoxLayout
- **Components:** JFrame, JTabbedPane, JTable, JOptionPane

### Architecture
- **3-Layer Architecture:**
  - Presentation Layer (Swing GUI)
  - Business Logic Layer (Services)
  - Data Access Layer (DAO with JDBC)

## 🏗️ Architecture

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│    (CPTrackerGUI / ConsoleUI)           │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│       Business Logic Layer              │
│  (ProblemService / ProgressService)     │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│        Data Access Layer                │
│  (ProblemDAOImpl / ProgressDAOImpl)     │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│          MySQL Database                 │
│   (users, problems, progress)           │
└─────────────────────────────────────────┘
```

## 📦 Installation

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7+
- Git

### Step 1: Clone Repository
```bash
git clone https://github.com/QuantumGod007/CP-Progress-Trackerd.git
cd CP-Progress-Trackerd
```

### Step 2: Setup Database
```bash
# Login to MySQL
mysql -u root -p

# Run schema
SOURCE /path/to/CP-Progress-Trackerd/database/schema.sql;

# Load sample data (optional)
SOURCE /path/to/CP-Progress-Trackerd/database/sample_data.sql;
```

### Step 3: Configure Database Connection
Edit `src/com/cptracker/util/DBConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/cp_tracker";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password"; // Change this
```

### Step 4: Compile
```bash
javac -d bin -cp "lib/*" $(find src -name "*.java")
```

### Step 5: Run
```bash
# GUI Version (Recommended)
./run-gui.sh

# OR Console Version
./run.sh

# OR Direct command
java -cp "lib/*:bin" com.cptracker.ui.CPTrackerGUI
```

## 🎮 Usage

### GUI Version

#### 1. Add Problem
- Click "Add Problem" tab
- Fill in: Title, Platform, Difficulty, Status, Notes
- Click "Add Problem" button

#### 2. View Problems
- Click "View Problems" tab
- See all problems in table
- Select a problem and:
  - Click "View Notes" to see notes
  - Click "Mark as Solved" to update status
  - Click "Delete" to remove problem

#### 3. View Progress
- Click "Progress" tab
- See statistics: Total Solved, Accuracy, Streak, Difficulty Breakdown
- Click "Refresh Statistics" to update

### Console Version
```bash
./run.sh
```
Follow the menu-driven interface (1-6 options)

## 💾 Database Schema

### Tables

#### users
```sql
user_id (PK)
username (UNIQUE)
email
created_at
```

#### problems
```sql
problem_id (PK)
user_id (FK)
title
platform
difficulty (ENUM: EASY, MEDIUM, HARD)
status (ENUM: SOLVED, ATTEMPTED, TODO)
solved_date
attempts
notes
created_at
```

#### progress
```sql
progress_id (PK)
user_id (FK, UNIQUE)
total_solved
current_streak
longest_streak
accuracy
last_solved_date
updated_at
```

### Relationships
- One user → Many problems
- One user → One progress record

## 📁 Project Structure

```
CP-Progress-Trackerd/
├── src/
│   └── com/cptracker/
│       ├── model/              # Entity classes (POJOs)
│       │   ├── User.java
│       │   ├── Problem.java
│       │   └── Progress.java
│       ├── dao/                # Data Access Layer
│       │   ├── UserDAO.java
│       │   ├── ProblemDAO.java
│       │   ├── ProgressDAO.java
│       │   └── impl/
│       │       ├── ProblemDAOImpl.java
│       │       └── ProgressDAOImpl.java
│       ├── service/            # Business Logic Layer
│       │   ├── ProblemService.java
│       │   └── ProgressService.java
│       ├── util/               # Utilities
│       │   └── DBConnection.java
│       └── ui/                 # User Interface
│           ├── CPTrackerGUI.java    (Swing)
│           └── ConsoleUI.java       (Console)
├── database/
│   ├── schema.sql              # Database structure
│   ├── sample_data.sql         # Test data
│   └── queries.sql             # Useful queries
├── lib/
│   └── mysql-connector-j-8.3.0.jar
├── bin/                        # Compiled classes
├── run.sh                      # Console launcher
├── run-gui.sh                  # GUI launcher
├── .gitignore
└── README.md
```

## 🎓 OOP Concepts Demonstrated

- ✅ **Encapsulation** - Private fields with getters/setters
- ✅ **Abstraction** - DAO interfaces
- ✅ **Inheritance** - Interface implementation
- ✅ **Polymorphism** - Interface-based programming
- ✅ **SOLID Principles** - Single Responsibility, Dependency Inversion

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Anvith Shetty**
- GitHub: [@QuantumGod007](https://github.com/QuantumGod007)
- Email: anvith320@gmail.com

---

**⭐ If you found this project helpful, please give it a star!**

**Happy Coding! 🚀**
