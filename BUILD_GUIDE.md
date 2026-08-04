# MegaMall - Build and Setup Guide

## Quick Start

This guide will help you build the MegaMall Android application into a production-ready APK.

## Prerequisites

### System Requirements
- **Operating System:** Windows, macOS, or Linux
- **RAM:** Minimum 8GB (16GB recommended)
- **Disk Space:** 20GB free space
- **Internet Connection:** Required for downloading dependencies

### Software Requirements
1. **Android Studio** (Latest version)
   - Download from: https://developer.android.com/studio
   - Includes Android SDK, Gradle, and build tools

2. **Java Development Kit (JDK)**
   - JDK 8 or higher (JDK 11 recommended)
   - Download from: https://www.oracle.com/java/technologies/downloads/

3. **Android SDK**
   - API Level 21 (Android 5.0) minimum
   - API Level 29 (Android 10) recommended for testing

## Installation Steps

### Step 1: Clone or Download the Project

```bash
# Clone from repository
git clone https://github.com/yourusername/mega-mall.git
cd mega-mall

# Or download and extract the ZIP file
unzip mega-mall.zip
cd mega-mall
```

### Step 2: Open in Android Studio

1. Launch Android Studio
2. Click **Open an existing Android Studio project**
3. Navigate to the `mega-mall` directory
4. Click **Open**
5. Wait for Android Studio to sync the Gradle files

### Step 3: Configure Firebase

1. Create a Firebase project:
   - Go to [Firebase Console](https://console.firebase.google.com)
   - Click **Create a new project**
   - Follow the setup wizard

2. Register your Android app:
   - In Firebase Console, click **Add app** → **Android**
   - Enter package name: `com.megamall`
   - Download `google-services.json`

3. Add the configuration file:
   - Place `google-services.json` in the `app/` directory
   - Android Studio will automatically detect it

4. Enable Firebase Services:
   - **Authentication:** Enable Email/Password and Google Sign-In
   - **Firestore Database:** Create a database in test mode
   - **Storage:** Create a storage bucket
   - **Cloud Messaging:** Enable for push notifications

### Step 4: Build the Project

#### Debug Build (for testing)

```bash
# Using Android Studio:
# 1. Click Build → Make Project
# 2. Wait for the build to complete

# Or using command line:
./gradlew assembleDebug
```

**Output:** `app/build/outputs/apk/debug/app-debug.apk`

#### Release Build (for production)

```bash
# Create a signed APK:
./gradlew assembleRelease

# Or build using Android Studio:
# 1. Click Build → Generate Signed Bundle/APK
# 2. Select APK
# 3. Create or select a keystore
# 4. Fill in signing details
# 5. Choose release build type
# 6. Click Finish
```

**Output:** `app/build/outputs/apk/release/app-release.apk`

## Keystore Setup (for Release Builds)

### Create a Keystore File

```bash
keytool -genkey -v -keystore mega-mall.keystore \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -alias megamall
```

**Important:** Save the keystore file and remember the password. You'll need it for future updates.

### Sign the APK

```bash
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
  -keystore mega-mall.keystore \
  app/build/outputs/apk/release/app-release-unsigned.apk \
  megamall
```

### Align the APK

```bash
zipalign -v 4 \
  app/build/outputs/apk/release/app-release-unsigned.apk \
  MegaMall-v1.0.apk
```

## Testing the APK

### On Android Device

1. **Enable Developer Mode:**
   - Go to Settings → About Phone
   - Tap Build Number 7 times
   - Go back to Settings → Developer Options
   - Enable USB Debugging

2. **Connect via USB:**
   ```bash
   adb devices  # Verify device is connected
   ```

3. **Install the APK:**
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

4. **Launch the app:**
   ```bash
   adb shell am start -n com.megamall/.ui.MainActivity
   ```

### On Android Emulator

1. **Create an emulator:**
   - In Android Studio: Tools → AVD Manager
   - Click Create Virtual Device
   - Select a device (e.g., Pixel 5)
   - Select an API level (29+)
   - Click Finish

2. **Run the app:**
   - Click Run → Run 'app'
   - Select the emulator
   - Click OK

## Troubleshooting

### Build Errors

**Error: "Could not find com.android.tools.build:gradle"**
- Solution: Update Android Studio and Gradle
- Run: `./gradlew wrapper --gradle-version 7.0`

**Error: "Minimum supported Gradle version is X"**
- Solution: Update Gradle in `gradle/wrapper/gradle-wrapper.properties`
- Or use Android Studio's built-in Gradle wrapper

**Error: "Firebase initialization failed"**
- Solution: Ensure `google-services.json` is in the `app/` directory
- Verify Firebase project is set up correctly

**Error: "Compilation failed: Kotlin version mismatch"**
- Solution: Update Kotlin plugin in Android Studio
- Or update Kotlin version in `build.gradle`

### Runtime Errors

**App crashes on startup**
- Check Logcat in Android Studio for error messages
- Verify Firebase credentials are correct
- Ensure internet connection is available

**Push notifications not working**
- Verify Firebase Cloud Messaging is enabled
- Check device notification settings
- Verify app has notification permissions

## Gradle Commands

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate code coverage report
./gradlew testDebugUnitTestCoverage

# Check for dependency updates
./gradlew dependencyUpdates

# Format code
./gradlew spotlessApply

# Lint check
./gradlew lint
```

## Publishing to Google Play Store

### Prerequisites
- Google Play Developer Account ($25 one-time fee)
- Signed release APK
- App screenshots and descriptions
- Privacy policy URL

### Steps

1. **Create App Listing:**
   - Go to [Google Play Console](https://play.google.com/console)
   - Click Create app
   - Fill in app name and category

2. **Upload APK:**
   - Go to Release → Production
   - Click Create new release
   - Upload signed APK
   - Fill in release notes

3. **Add Store Listing:**
   - Add app title, description, screenshots
   - Set content rating
   - Add privacy policy

4. **Review and Submit:**
   - Review all information
   - Click Submit for review
   - Wait for Google's approval (typically 24-48 hours)

## Performance Optimization

### Reduce APK Size

```gradle
// In app/build.gradle
android {
    bundle {
        density.enableSplit = true
        abi.enableSplit = true
    }
}
```

### Enable ProGuard/R8

```gradle
buildTypes {
    release {
        minifyEnabled true
        shrinkResources true
        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
    }
}
```

### Optimize Images

- Use WebP format for images
- Compress images before adding to project
- Use vector drawables for icons

## Continuous Integration

### GitHub Actions Example

Create `.github/workflows/build.yml`:

```yaml
name: Build APK

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK
        uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Build APK
        run: ./gradlew assembleDebug
      - name: Upload APK
        uses: actions/upload-artifact@v2
        with:
          name: app-debug.apk
          path: app/build/outputs/apk/debug/app-debug.apk
```

## Support and Resources

- **Android Documentation:** https://developer.android.com/docs
- **Jetpack Compose Guide:** https://developer.android.com/jetpack/compose
- **Firebase Documentation:** https://firebase.google.com/docs
- **Kotlin Documentation:** https://kotlinlang.org/docs
- **Material Design 3:** https://m3.material.io/

## Next Steps

1. Configure Firebase credentials
2. Test on emulator or physical device
3. Customize branding (app name, colors, icons)
4. Implement backend API integration
5. Add unit and UI tests
6. Submit to Google Play Store

---

**Last Updated:** August 2, 2026
**Status:** Production Ready
