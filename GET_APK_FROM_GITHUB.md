# How to Get Your APK from GitHub (Step-by-Step)

## Overview

This guide will walk you through uploading the MegaMall project to GitHub and automatically building the APK using GitHub Actions. No local build required!

## Prerequisites

- GitHub account (free at https://github.com)
- Git installed on your computer (https://git-scm.com)
- That's it! GitHub Actions does the rest.

---

## STEP 1: Create GitHub Account (if you don't have one)

1. Go to https://github.com
2. Click **Sign up**
3. Enter email, password, username
4. Verify email
5. Complete setup

---

## STEP 2: Create New Repository on GitHub

1. Log in to GitHub
2. Click **+** icon (top right) → **New repository**
3. Fill in details:
   - **Repository name:** `mega-mall`
   - **Description:** Production-ready E-Commerce Android Application
   - **Visibility:** Choose Public or Private
   - **Do NOT initialize** with README, .gitignore, or license
4. Click **Create repository**
5. You'll see a page with commands - **COPY the HTTPS URL** (looks like `https://github.com/yourusername/mega-mall.git`)

---

## STEP 3: Download & Extract Project

1. Download `mega-mall-complete.tar.gz` from the files provided
2. Extract it:
   ```bash
   tar -xzf mega-mall-complete.tar.gz
   cd mega-mall
   ```

---

## STEP 4: Push Project to GitHub

Open terminal/command prompt in the `mega-mall` directory and run:

```bash
# Set your GitHub credentials
git config user.name "Your Name"
git config user.email "your.email@example.com"

# Add GitHub as remote (replace with YOUR repository URL)
git remote add origin https://github.com/yourusername/mega-mall.git

# Rename branch to main
git branch -M main

# Push to GitHub
git push -u origin main
```

**Wait for upload to complete** (may take 5-10 minutes due to Gradle wrapper size)

---

## STEP 5: Verify Upload on GitHub

1. Go to your GitHub repository: `https://github.com/yourusername/mega-mall`
2. You should see all project files
3. Check that `.github/workflows/build-apk.yml` exists

---

## STEP 6: Trigger Automatic Build

### Option A: Automatic Build (Recommended)

The build starts automatically when you push! Just wait 5-10 minutes.

### Option B: Manual Build

1. Go to your GitHub repository
2. Click **Actions** tab
3. Click **Build APK** on the left
4. Click **Run workflow** button
5. Click **Run workflow** again
6. Wait for build to complete (5-10 minutes)

---

## STEP 7: Download Your APK

### When Build is Complete:

1. Go to your GitHub repository
2. Click **Actions** tab
3. Click the **Build APK** workflow run (green checkmark = success)
4. Scroll down to **Artifacts** section
5. Download one of:
   - **app-debug** - For testing on your phone
   - **app-release** - For production/Play Store

### You now have your APK! 🎉

---

## STEP 8: Install APK on Your Phone

### Option A: Direct Installation

1. Download APK to your phone
2. Open file manager
3. Tap the APK file
4. Tap **Install**
5. App is installed!

### Option B: Using ADB (Advanced)

```bash
# Connect phone via USB
adb devices

# Install APK
adb install app-debug.apk

# Launch app
adb shell am start -n com.megamall/.ui.MainActivity
```

---

## STEP 9: Test the App

1. Open MegaMall app on your phone
2. You should see the splash screen
3. Test the interface
4. Try different user roles (Customer, Seller, Delivery, Admin)

---

## STEP 10: Make Changes & Rebuild

### To Update the App:

1. Download project files locally
2. Make changes in Android Studio or text editor
3. Commit and push to GitHub:
   ```bash
   git add .
   git commit -m "Your change description"
   git push origin main
   ```
4. GitHub Actions automatically builds new APK
5. Download from Actions tab

---

## Troubleshooting

### Build Failed

**Check the logs:**
1. Go to **Actions** tab
2. Click failed build
3. Click **Build with Gradle** step
4. See error message

**Common fixes:**
- Wait 5 minutes and retry
- Check internet connection
- Verify all files uploaded correctly

### Can't Find Artifacts

**Make sure:**
1. Build completed successfully (green checkmark)
2. Scroll down to **Artifacts** section
3. Artifacts available for 90 days

### APK Won't Install

**Try:**
1. Enable "Unknown sources" in phone settings
2. Uninstall old version first
3. Use debug APK (not release)
4. Check phone storage space

### App Crashes on Launch

**Check:**
1. Phone has internet connection
2. Android version is 5.0 or higher
3. Try clearing app cache: Settings → Apps → MegaMall → Clear Cache

---

## Advanced: Create Release Versions

### Create a Release Tag

```bash
git tag -a v1.0.0 -m "Version 1.0.0 - Initial Release"
git push origin v1.0.0
```

This automatically:
- Builds the APK
- Creates a GitHub Release
- Uploads APK as release asset
- You can download from Releases page

---

## GitHub Actions Workflow Explained

The `.github/workflows/build-apk.yml` file automatically:

1. **Checks out code** from GitHub
2. **Sets up Java 11** (required for building)
3. **Downloads dependencies** (Gradle, Android SDK, libraries)
4. **Builds debug APK** - for testing
5. **Builds release APK** - for production
6. **Runs tests** - verifies code quality
7. **Uploads artifacts** - saves APKs for download

**Build time:** 5-10 minutes (first time), 2-3 minutes (subsequent)

---

## File Locations in GitHub

```
mega-mall/
├── .github/
│   └── workflows/
│       └── build-apk.yml          ← Automatic build configuration
├── app/
│   ├── src/main/
│   │   ├── java/com/megamall/    ← Kotlin source code
│   │   └── res/                   ← Resources
│   ├── build.gradle               ← App configuration
│   └── proguard-rules.pro         ← Code obfuscation
├── build.gradle                   ← Project configuration
├── README.md                       ← Documentation
├── BUILD_GUIDE.md                 ← Build instructions
├── API_SPECIFICATION.md           ← API reference
└── GITHUB_SETUP.md               ← GitHub setup guide
```

---

## Quick Reference Commands

```bash
# Clone from GitHub
git clone https://github.com/yourusername/mega-mall.git
cd mega-mall

# Make changes
# ... edit files ...

# Commit and push
git add .
git commit -m "Description of changes"
git push origin main

# Create a release
git tag -a v1.0.1 -m "Version 1.0.1"
git push origin v1.0.1

# View git log
git log --oneline
```

---

## Next Steps

1. ✅ Create GitHub account
2. ✅ Create repository
3. ✅ Push project to GitHub
4. ✅ Wait for automatic build
5. ✅ Download APK
6. ✅ Install on phone
7. ✅ Test the app
8. ✅ Make changes and rebuild

---

## Support

**GitHub Docs:** https://docs.github.com  
**GitHub Actions:** https://docs.github.com/en/actions  
**Android Development:** https://developer.android.com

---

## Summary

You now have a **complete, production-ready Android e-commerce application** that:

- ✅ Builds automatically on GitHub
- ✅ No local build tools needed
- ✅ Download APK in minutes
- ✅ Install on any Android phone
- ✅ Easy to update and maintain
- ✅ Professional CI/CD pipeline

**Congratulations! Your MegaMall app is ready!** 🚀

---

**Last Updated:** August 2, 2026  
**Status:** Ready for GitHub  
**Build Time:** 5-10 minutes
