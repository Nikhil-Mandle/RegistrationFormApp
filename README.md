# Registration Form Assignment

This project is a sample Android application that demonstrates a multi-page registration form using Jetpack Compose for UI, Dagger Hilt for dependency injection, and DataStore preferences for data persistence. Compose Navigation is used for navigating between different pages of the registration form.

## Features

- **Basic Page:** Collects basic information such as first name, last name, gender, phone number, password, and confirm password. 
Includes form validation for required fields and password matching.

- **Professional Details Page:** Collects professional details such as education, year of passing, university, and grade. 
Includes specific validation requirements for each field.

- **Address Details Page:** Collects address details such as street, city, state, and pin code.

- **Dependency Injection:** Uses Dagger Hilt for dependency injection to manage dependencies and improve code maintainability.

- **Data Persistence:** Uses DataStore preferences to store user data such as first name, last name, phone number, email, gender, education, year of passing, university, grade, street, city, state, and pin code.

- **Navigation:** Uses Compose Navigation to navigate between different pages of the registration form.

## Installation

1. Clone the repository: `git clone `
2. Open the project in Android Studio.
3. Build and run the project on an Android emulator or device.

## Version Catalog

The following is a summary of the project's features and requirements:

In the gradle.kts(app)
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

dependencies {
...
 // Navigation
    implementation(libs.androidx.navigation.compose)

    // Coil
    implementation(libs.coil.compose)

    // Datastore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Hilt DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    }
    
    ```toml
    [versions]
    coilCompose = "2.6.0"
    datastorePreferences = "1.1.1"
    hiltAndroid = "2.48"
    navigationCompose = "2.7.7"
    
    [libraries]
    androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose"}
    coil-compose = { module = "io.coil-kt:coil-compose", version.ref = "coilCompose" }
    androidx-datastore-preferences = { module = "androidx.datastore:datastore-preferences", version.ref = "datastorePreferences" }
    hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "hiltAndroid" }
    hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hiltAndroid" }

    ```

## Screenshots

**Application Supports Dark Theme and Light Theme**
Screenshots attached in the screenshot folder

## Requirements

- Android Studio
- Android SDK min version 24 or newer
- Kotlin version 1.9.0
- Dagger Hilt version 2.48
- Jetpack Compose version 1.0.0 or newer    
- DataStore preferences
