# DAO Interfaces Documentation

## Overview
DAO (Data Access Object) pattern separates business logic from database operations.

---

## 1. UserDAO Interface

### CRUD Operations:

| Method | Purpose | Parameters | Returns |
|--------|---------|------------|---------|
| `addUser()` | Insert new user | User object | void |
| `getUserById()` | Get user by ID | userId (int) | User |
| `getUserByUsername()` | Get user by username | username (String) | User |
| `getAllUsers()` | Get all users | none | List<User> |
| `updateUser()` | Update user info | User object | void |
| `deleteUser()` | Delete user | userId (int) | void |

---

## 2. ProblemDAO Interface

### CRUD Operations:

| Method | Purpose | Parameters | Returns |
|--------|---------|------------|---------|
| `addProblem()` | Insert new problem | Problem object | void |
| `getProblemById()` | Get problem by ID | problemId (int) | Problem |
| `getAllProblems()` | Get all problems for user | userId (int) | List<Problem> |
| `getProblemsByStatus()` | Filter by status | userId, status | List<Problem> |
| `getProblemsByDifficulty()` | Filter by difficulty | userId, difficulty | List<Problem> |
| `getProblemsByPlatform()` | Filter by platform | userId, platform | List<Problem> |
| `updateProblem()` | Update problem | Problem object | void |
| `markAsSolved()` | Mark problem solved | problemId (int) | void |
| `incrementAttempts()` | Increase attempt count | problemId (int) | void |
| `deleteProblem()` | Delete problem | problemId (int) | void |

---

## 3. ProgressDAO Interface

### CRUD Operations:

| Method | Purpose | Parameters | Returns |
|--------|---------|------------|---------|
| `addProgress()` | Create progress record | Progress object | void |
| `getProgressByUserId()` | Get user progress | userId (int) | Progress |
| `updateProgress()` | Update progress | Progress object | void |
| `updateTotalSolved()` | Recalculate total solved | userId (int) | void |
| `updateStreak()` | Recalculate streak | userId (int) | void |
| `updateAccuracy()` | Recalculate accuracy | userId (int) | void |
| `deleteProgress()` | Delete progress | userId (int) | void |

---

## Design Benefits

### 1. Abstraction
- Business logic doesn't know about SQL
- Easy to switch databases

### 2. Testability
- Can create mock implementations
- Unit testing without database

### 3. Maintainability
- Changes in one place
- Clear separation of concerns

### 4. Reusability
- Same interface, multiple implementations
- Can have MySQL, PostgreSQL, MongoDB versions

---

## Usage Example

```java
// In Service or Main class
UserDAO userDAO = new UserDAOImpl();

// Create
User user = new User("john_doe", "john@example.com");
userDAO.addUser(user);

// Read
User retrieved = userDAO.getUserById(1);
List<User> allUsers = userDAO.getAllUsers();

// Update
retrieved.setEmail("newemail@example.com");
userDAO.updateUser(retrieved);

// Delete
userDAO.deleteUser(1);
```

---

## Next Steps

1. ✅ DAO Interfaces created
2. ⏭️ Create DAO implementations (UserDAOImpl, ProblemDAOImpl, ProgressDAOImpl)
3. ⏭️ Implement JDBC logic in each method
4. ⏭️ Test with database
