# GitHub Setup & Automated APK Building

## Overview

This guide will help you set up the MegaMall project on GitHub and use GitHub Actions to automatically build the APK.

## Step 1: Create a GitHub Repository

### 1.1 Create New Repository

1. Go to [GitHub](https://github.com)
2. Click the **+** icon in the top right
3. Select **New repository**
4. Fill in the details:
   - **Repository name:** `mega-mall`
   - **Description:** Production-ready E-Commerce Android Application
   - **Visibility:** Public (or Private)
   - **Initialize with:** None (we'll push existing code)
5. Click **Create repository**

### 1.2 Get Repository URL

After creating, you'll see a URL like:
```
https://github.com/yourusername/mega-mall.git
```

## Step 2: Push Code to GitHub

### 2.1 Initialize Git (if not already done)

```bash
cd mega-mall
git init
git add .
git commit -m "Initial commit: Complete MegaMall e-commerce application"
```

### 2.2 Add Remote and Push

```bash
git remote add origin https://github.com/yourusername/mega-mall.git
git branch -M main
git push -u origin main
```

### 2.3 Verify Upload

- Go to your GitHub repository
- Verify all files are uploaded
- You should see the project structure

## Step 3: GitHub Actions Setup

### 3.1 Workflow File Location

The workflow file is already created at:
```
.github/workflows/build-apk.yml
```

This file will automatically:
- Build debug APK
- Build release APK
- Run tests
- Run lint checks
- Upload artifacts

### 3.2 Verify Workflow

1. Go to your GitHub repository
2. Click **Actions** tab
3. You should see "Build APK" workflow
4. If not, the workflow file may need to be committed

## Step 4: Trigger Builds

### 4.1 Automatic Builds

Builds are triggered automatically on:
- **Push to main branch**
- **Push to develop branch**
- **Pull requests**

### 4.2 Manual Builds

To manually trigger a build:

1. Go to **Actions** tab
2. Click **Build APK** workflow
3. Click **Run workflow** button
4. Select branch (main or develop)
5. Click **Run workflow**

### 4.3 View Build Progress

1. Go to **Actions** tab
2. Click the workflow run
3. Watch the build progress in real-time
4. See logs for each step

## Step 5: Download APK

### 5.1 After Successful Build

1. Go to **Actions** tab
2. Click the completed workflow run
3. Scroll down to **Artifacts** section
4. Download:
   - **app-debug** - Debug APK for testing
   - **app-release** - Release APK for production

### 5.2 APK Locations

The APKs are built at:
- **Debug:** `app/build/outputs/apk/debug/app-debug.apk`
- **Release:** `app/build/outputs/apk/release/app-release-unsigned.apk`

## Step 6: Create Release Tags

### 6.1 Create a Release Tag

```bash
git tag -a v1.0.0 -m "Version 1.0.0 - Initial Release"
git push origin v1.0.0
```

### 6.2 Automatic Release Build

When you push a tag:
1. GitHub Actions automatically builds the APK
2. Creates a GitHub Release
3. Uploads APK as release asset
4. You can download from Releases page

## Step 7: Configure Secrets (Optional)

For advanced features like signing APKs automatically:

### 7.1 Add Secrets

1. Go to **Settings** → **Secrets and variables** → **Actions**
2. Click **New repository secret**
3. Add secrets:
   - `KEYSTORE_FILE` - Base64 encoded keystore
   - `KEYSTORE_PASSWORD` - Keystore password
   - `KEY_ALIAS` - Key alias
   - `KEY_PASSWORD` - Key password

### 7.2 Use Secrets in Workflow

Update `.github/workflows/build-apk.yml` to use secrets:

```yaml
- name: Sign Release APK
  run: |
    echo "${{ secrets.KEYSTORE_FILE }}" | base64 -d > keystore.jks
    jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
      -keystore keystore.jks \
      -storepass ${{ secrets.KEYSTORE_PASSWORD }} \
      -keypass ${{ secrets.KEY_PASSWORD }} \
      app/build/outputs/apk/release/app-release-unsigned.apk \
      ${{ secrets.KEY_ALIAS }}
```

## Step 8: Monitor Builds

### 8.1 Build Status Badge

Add to README.md:

```markdown
[![Build Status](https://github.com/yourusername/mega-mall/workflows/Build%20APK/badge.svg)](https://github.com/yourusername/mega-mall/actions)
```

### 8.2 View Build History

1. Go to **Actions** tab
2. See all workflow runs
3. Click any run to see details
4. View logs for debugging

## Step 9: Troubleshooting

### Build Fails

1. Check the workflow logs:
   - Go to **Actions** → Failed run
   - Click **Build with Gradle** step
   - See error message

2. Common issues:
   - **Java version mismatch:** Workflow uses Java 11
   - **Gradle cache:** Clear cache and retry
   - **Missing dependencies:** Check internet connection

### Download APK

1. Go to **Actions** → Latest successful run
2. Scroll to **Artifacts**
3. Click download button
4. APK will download

### Test APK

```bash
# Install on device
adb install app-debug.apk

# Or on emulator
adb install app-debug.apk
```

## Step 10: Next Steps

### 10.1 Continuous Improvement

1. Make code changes locally
2. Commit and push to GitHub
3. GitHub Actions automatically builds
4. Download and test APK
5. Create release tags for versions

### 10.2 Collaborate

1. Invite team members to repository
2. Create branches for features
3. Create pull requests
4. GitHub Actions tests automatically
5. Merge after approval

### 10.3 Deploy to Play Store

1. Download signed APK from releases
2. Go to Google Play Console
3. Upload APK
4. Fill in store listing
5. Submit for review

## Useful Commands

```bash
# Clone repository
git clone https://github.com/yourusername/mega-mall.git
cd mega-mall

# Create feature branch
git checkout -b feature/new-feature

# Commit changes
git add .
git commit -m "Add new feature"

# Push to GitHub
git push origin feature/new-feature

# Create pull request on GitHub

# Merge to main
git checkout main
git merge feature/new-feature
git push origin main

# Create release tag
git tag -a v1.0.1 -m "Version 1.0.1"
git push origin v1.0.1
```

## GitHub Actions Workflow Details

### Build Steps

1. **Checkout code** - Download repository
2. **Setup JDK 11** - Install Java 11
3. **Grant permissions** - Make gradlew executable
4. **Build APK** - Compile debug and release APKs
5. **Upload artifacts** - Save APKs for download
6. **Run tests** - Execute unit tests
7. **Run lint** - Check code quality

### Build Time

- First build: 5-10 minutes (downloads dependencies)
- Subsequent builds: 2-3 minutes (uses cache)

### Storage

- Artifacts stored for 90 days
- Older artifacts automatically deleted
- Releases stored indefinitely

## Support

- **GitHub Docs:** https://docs.github.com
- **GitHub Actions:** https://docs.github.com/en/actions
- **Android Gradle Plugin:** https://developer.android.com/studio/build

---

**Last Updated:** August 2, 2026
**Status:** Ready for GitHub
