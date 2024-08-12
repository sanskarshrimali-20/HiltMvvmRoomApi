// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
        classpath("com.google.gms:google-services:4.4.0")
    }
    repositories {
        google()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.devtools.ksp") version  "1.9.21-1.0.16" apply false
    id("com.android.library") version "8.2.1" apply false
    id("com.android.dynamic-feature") version "8.2.1" apply false
}