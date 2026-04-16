# DBConnection Setup Guide

## 📋 Prerequisites

### 1. Download MySQL JDBC Driver

**Option A: Direct Download**
- Visit: https://dev.mysql.com/downloads/connector/j/
- Download: `mysql-connector-java-8.x.x.jar`
- Save to: `/Users/anvith_007/Desktop/CP_MINI/lib/`

**Option B: Maven (if using)**
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

---

## ⚙️ Configuration

### Update Database Password

Edit `DBConnection.java` line 14:
```java
private static final String PASSWORD = "your_actual_password";
```

---

## 🚀 Compile and Run

### Step 1: Create lib directory
```bash
cd /Users/anvith_007/Desktop/CP_MINI
mkdir -p lib
```

### Step 2: Download and place JDBC driver
Place `mysql-connector-java-8.x.x.jar` in the `lib/` folder

### Step 3: Compile
```bash
# Compile DBConnection
javac -d bin -cp "lib/*" src/com/cptracker/util/DBConnection.java

# Compile TestConnection
javac -d bin -cp "lib/*:bin" src/com/cptracker/util/TestConnection.java
```

### Step 4: Run Test
```bash
java -cp "lib/*:bin" com.cptracker.util.TestConnection
```

---

## ✅ Expected Output

```
Testing Database Connection...

✓ Database connection successful!
✓ Connected to: jdbc:mysql://localhost:3306/cp_tracker

==================================================

✓ Manual connection test successful!
✓ Connection object: com.mysql.cj.jdbc.ConnectionImpl@xxxxx
Connection closed successfully.
```

---

## 🐛 Troubleshooting

### Error: ClassNotFoundException
**Problem:** JDBC driver not found  
**Solution:** 
- Verify `mysql-connector-java-8.x.x.jar` is in `lib/` folder
- Check classpath includes `lib/*`

### Error: Access denied for user 'root'
**Problem:** Wrong password  
**Solution:** Update PASSWORD in DBConnection.java

### Error: Unknown database 'cp_tracker'
**Problem:** Database not created  
**Solution:** Run `schema.sql` first

### Error: Communications link failure
**Problem:** MySQL server not running  
**Solution:** 
```bash
# macOS
brew services start mysql

# Linux
sudo systemctl start mysql

# Windows
net start MySQL
```

---

## 📁 Project Structure

```
CP_MINI/
├── lib/
│   └── mysql-connector-java-8.x.x.jar
├── src/
│   └── com/cptracker/util/
│       ├── DBConnection.java
│       └── TestConnection.java
├── bin/                    (compiled classes)
└── database/
    ├── schema.sql
    └── sample_data.sql
```

---

## 🔑 Key Concepts Explained

### 1. DriverManager
- Manages JDBC drivers
- Creates database connections
- `getConnection()` returns Connection object

### 2. Connection Object
- Represents database session
- Used to create Statement/PreparedStatement
- Must be closed after use

### 3. Class.forName()
- Loads JDBC driver class
- Required for older JDBC versions
- Modern JDBC auto-loads drivers

### 4. Try-with-resources
```java
try (Connection conn = DBConnection.getConnection()) {
    // Auto-closes connection
}
```

---

## 📝 Usage in DAO Classes

```java
public class UserDAO {
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

---

## ⏭️ Next Steps

1. ✅ DBConnection created
2. ⏭️ Create Model classes (User, Problem, Progress)
3. ⏭️ Create DAO interfaces
4. ⏭️ Implement DAO classes using DBConnection
