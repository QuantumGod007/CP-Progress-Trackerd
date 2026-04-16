# 📤 GitHub Upload Guide - Step by Step

## 🎯 Complete Guide to Upload CP Progress Tracker to GitHub

---

## ✅ Pre-Upload Checklist

Before uploading, ensure:
- [x] All code is working
- [x] Database password is NOT hardcoded (or use placeholder)
- [x] .gitignore file is created
- [x] README is ready
- [x] No sensitive data in code

---

## 📋 Step-by-Step Instructions

### **Step 1: Update Database Password (IMPORTANT)**

**Option A: Use Placeholder**
Edit `src/com/cptracker/util/DBConnection.java`:
```java
private static final String PASSWORD = "your_password"; // Users will change this
```

**Option B: Use Environment Variable (Advanced)**
```java
private static final String PASSWORD = System.getenv("DB_PASSWORD");
```

---

### **Step 2: Initialize Git Repository**

Open Terminal in your project folder:
```bash
cd /Users/anvith_007/Desktop/CP_MINI

# Initialize Git
git init

# Check status
git status
```

---

### **Step 3: Add Files to Git**

```bash
# Add all files (respects .gitignore)
git add .

# Check what will be committed
git status

# You should see:
# - src/ files
# - database/ files
# - lib/ files (JDBC driver)
# - README.md
# - .gitignore
# - run scripts

# You should NOT see:
# - bin/ folder (compiled classes)
# - .DS_Store
# - *.class files
```

---

### **Step 4: Create First Commit**

```bash
git commit -m "Initial commit: CP Progress Tracker with Swing GUI"
```

---

### **Step 5: Create GitHub Repository**

1. Go to [GitHub.com](https://github.com)
2. Click **"+"** (top right) → **"New repository"**
3. Fill in details:
   - **Repository name:** `CP-Progress-Tracker` or `CP_MINI`
   - **Description:** "Java-based Competitive Programming Progress Tracker with MySQL and Swing GUI"
   - **Visibility:** Public (or Private)
   - **DO NOT** initialize with README (we already have one)
4. Click **"Create repository"**

---

### **Step 6: Connect Local to GitHub**

GitHub will show commands. Use these:

```bash
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/CP-Progress-Tracker.git

# Verify remote
git remote -v

# Push to GitHub
git branch -M main
git push -u origin main
```

**Replace `YOUR_USERNAME` with your actual GitHub username!**

---

### **Step 7: Verify Upload**

1. Refresh your GitHub repository page
2. You should see:
   - All source files
   - Database scripts
   - README.md displayed
   - Folder structure

---

### **Step 8: Add README as Main File**

```bash
# Rename GITHUB_README.md to README.md
mv GITHUB_README.md README.md

# Commit and push
git add README.md
git commit -m "Update README for GitHub"
git push
```

---

### **Step 9: Create Screenshots Folder (Optional)**

```bash
# Create screenshots folder
mkdir screenshots

# Add placeholder
echo "Add screenshots here" > screenshots/README.txt

# Commit
git add screenshots/
git commit -m "Add screenshots folder"
git push
```

**Later, add actual screenshots:**
1. Take screenshots of your GUI
2. Save as: `dashboard.png`, `add-problem.png`, `view-problems.png`, `progress.png`
3. Place in `screenshots/` folder
4. Commit and push

---

## 🔧 Common Issues & Solutions

### Issue 1: "Permission denied (publickey)"
**Solution:** Use HTTPS instead of SSH
```bash
git remote set-url origin https://github.com/YOUR_USERNAME/CP-Progress-Tracker.git
```

### Issue 2: "Updates were rejected"
**Solution:** Pull first, then push
```bash
git pull origin main --allow-unrelated-histories
git push origin main
```

### Issue 3: "Large files warning"
**Solution:** JDBC driver might be large. GitHub allows up to 100MB.
If issue persists, add to .gitignore and provide download link in README.

### Issue 4: "Password authentication deprecated"
**Solution:** Use Personal Access Token
1. GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token
3. Use token as password when pushing

---

## 📝 After Upload - Update README

Edit README.md on GitHub directly:
1. Click README.md
2. Click pencil icon (Edit)
3. Update:
   - Your GitHub username in links
   - Your email
   - Your name
   - Installation paths
4. Commit changes

---

## 🎨 Make Repository Look Professional

### Add Topics/Tags
1. Go to repository page
2. Click "⚙️ Settings" (or gear icon near About)
3. Add topics: `java`, `mysql`, `swing`, `jdbc`, `competitive-programming`, `gui`, `database`

### Add Description
In repository settings, add:
> "Java-based CP Progress Tracker with MySQL database and professional Swing GUI. Track problems, view statistics, and monitor your coding journey."

### Add License
1. Click "Add file" → "Create new file"
2. Name: `LICENSE`
3. Click "Choose a license template"
4. Select "MIT License"
5. Commit

---

## 🚀 Quick Commands Reference

```bash
# Check status
git status

# Add files
git add .

# Commit
git commit -m "Your message"

# Push
git push

# Pull latest
git pull

# View history
git log --oneline

# Create new branch
git checkout -b feature-name

# Switch branch
git checkout main
```

---

## 📊 Repository Structure on GitHub

```
CP-Progress-Tracker/
├── 📁 src/
│   └── 📁 com/cptracker/
│       ├── 📁 model/
│       ├── 📁 dao/
│       ├── 📁 service/
│       ├── 📁 util/
│       └── 📁 ui/
├── 📁 database/
│   ├── schema.sql
│   ├── sample_data.sql
│   └── queries.sql
├── 📁 lib/
│   └── mysql-connector-j-8.3.0.jar
├── 📁 screenshots/
│   ├── dashboard.png
│   ├── add-problem.png
│   ├── view-problems.png
│   └── progress.png
├── 📄 README.md
├── 📄 .gitignore
├── 📄 LICENSE
├── 🔧 run.sh
└── 🔧 run-gui.sh
```

---

## ✅ Final Checklist

Before sharing your repository:
- [ ] README is complete and accurate
- [ ] Screenshots are added
- [ ] Database password is placeholder
- [ ] All code is committed
- [ ] Repository is public (if you want to share)
- [ ] License is added
- [ ] Topics/tags are added
- [ ] Description is set
- [ ] Test clone and run on another machine

---

## 🎓 For Resume/Portfolio

Add to your resume:
```
CP Progress Tracker | Java, MySQL, Swing, JDBC
• Developed full-stack Java application with 3-layer architecture
• Implemented DAO pattern for database operations using JDBC
• Created professional Swing GUI with tabbed interface
• Integrated MySQL database with auto-calculated statistics
• GitHub: github.com/YOUR_USERNAME/CP-Progress-Tracker
```

---

## 📞 Need Help?

If you encounter issues:
1. Check GitHub documentation: https://docs.github.com
2. Search Stack Overflow
3. Check Git status: `git status`
4. Check remote: `git remote -v`

---

**🎉 Congratulations! Your project is now on GitHub!**

**Share your repository link:**
`https://github.com/YOUR_USERNAME/CP-Progress-Tracker`
