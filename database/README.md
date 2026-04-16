# MySQL Database Setup Guide

## Prerequisites
- MySQL Server installed (version 5.7 or higher)
- MySQL running on your system

---

## Step-by-Step Setup Instructions

### Method 1: Using MySQL Command Line

#### Step 1: Open Terminal/Command Prompt
```bash
# For macOS/Linux
mysql -u root -p

# For Windows
mysql -u root -p
```

#### Step 2: Run Schema File
```sql
SOURCE /Users/anvith_007/Desktop/CP_MINI/database/schema.sql;
```

#### Step 3: Run Sample Data File
```sql
SOURCE /Users/anvith_007/Desktop/CP_MINI/database/sample_data.sql;
```

#### Step 4: Verify Setup
```sql
-- Check database
SHOW DATABASES;

-- Use database
USE cp_tracker;

-- Check tables
SHOW TABLES;

-- View sample data
SELECT * FROM users;
SELECT * FROM problems;
SELECT * FROM progress;
```

---

### Method 2: Using MySQL Workbench

1. Open MySQL Workbench
2. Connect to your MySQL server
3. Go to **File → Open SQL Script**
4. Select `schema.sql` and click **Execute** (lightning bolt icon)
5. Select `sample_data.sql` and click **Execute**
6. Refresh the Schemas panel to see `cp_tracker` database

---

### Method 3: Using Command Line (One-liner)

```bash
# Navigate to project directory
cd /Users/anvith_007/Desktop/CP_MINI/database

# Run both files
mysql -u root -p < schema.sql
mysql -u root -p < sample_data.sql
```

---

## Database Configuration for JDBC

Add these details to your Java application:

```properties
DB_URL=jdbc:mysql://localhost:3306/cp_tracker
DB_USER=root
DB_PASSWORD=your_password
```

---

## Verify Installation

Run these queries to confirm everything works:

```sql
USE cp_tracker;

-- Count records
SELECT COUNT(*) FROM users;      -- Should return 3
SELECT COUNT(*) FROM problems;   -- Should return 10
SELECT COUNT(*) FROM progress;   -- Should return 3

-- Test JOIN query
SELECT u.username, p.title, p.difficulty, p.status
FROM users u
JOIN problems p ON u.user_id = p.user_id
WHERE u.username = 'john_doe';
```

---

## Troubleshooting

### Error: Access Denied
```bash
# Reset MySQL root password or use correct credentials
mysql -u root -p
```

### Error: Database Already Exists
```sql
-- Drop and recreate
DROP DATABASE IF EXISTS cp_tracker;
```

### Error: Cannot find file
```bash
# Use absolute path in SOURCE command
SOURCE /full/path/to/schema.sql;
```

---

## Quick Reference

| Table | Purpose |
|-------|---------|
| users | Store user information |
| problems | Track coding problems |
| progress | Store user statistics |

**Foreign Keys:**
- problems.user_id → users.user_id
- progress.user_id → users.user_id

**Cascade Delete:** Deleting a user removes all their problems and progress.

---

## Next Steps

1. ✅ Database setup complete
2. ⏭️ Create Java project structure
3. ⏭️ Add MySQL JDBC driver
4. ⏭️ Implement DAO classes
