#!/bin/bash

# GitHub Upload Script for CP Progress Tracker
# Run this script to complete the upload process

echo "╔════════════════════════════════════════╗"
echo "║  GitHub Upload - CP Progress Tracker  ║"
echo "╚════════════════════════════════════════╝"
echo ""

# Step 1: Add all files
echo "📦 Step 1: Adding files to Git..."
git add .
echo "✓ Files added"
echo ""

# Step 2: Create first commit
echo "💾 Step 2: Creating first commit..."
git commit -m "Initial commit: CP Progress Tracker with Swing GUI and MySQL integration"
echo "✓ Commit created"
echo ""

# Step 3: Instructions for GitHub
echo "🌐 Step 3: Create GitHub Repository"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "1. Go to: https://github.com/new"
echo "2. Repository name: CP-Progress-Tracker"
echo "3. Description: Java-based CP Progress Tracker with MySQL and Swing GUI"
echo "4. Choose: Public"
echo "5. DO NOT initialize with README"
echo "6. Click 'Create repository'"
echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Step 4: Get GitHub username
echo "📝 Step 4: Enter your GitHub username:"
read -p "Username: " github_username

if [ -z "$github_username" ]; then
    echo "❌ Username cannot be empty!"
    exit 1
fi

# Step 5: Connect to GitHub
echo ""
echo "🔗 Step 5: Connecting to GitHub..."
git remote add origin "https://github.com/$github_username/CP-Progress-Tracker.git"
echo "✓ Remote added"
echo ""

# Step 6: Push to GitHub
echo "🚀 Step 6: Pushing to GitHub..."
echo "You may be asked for your GitHub credentials..."
echo ""

git branch -M main
git push -u origin main

if [ $? -eq 0 ]; then
    echo ""
    echo "╔════════════════════════════════════════╗"
    echo "║  ✅ SUCCESS! Project uploaded!         ║"
    echo "╚════════════════════════════════════════╝"
    echo ""
    echo "🎉 Your repository is now live at:"
    echo "   https://github.com/$github_username/CP-Progress-Tracker"
    echo ""
    echo "📋 Next steps:"
    echo "   1. Add screenshots to screenshots/ folder"
    echo "   2. Update README.md with your details"
    echo "   3. Add topics/tags on GitHub"
    echo ""
else
    echo ""
    echo "❌ Push failed! Common issues:"
    echo "   1. Check your GitHub credentials"
    echo "   2. Make sure repository exists on GitHub"
    echo "   3. Try: git push -u origin main --force"
    echo ""
fi
