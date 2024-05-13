import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.hlopezg.data_remote"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val localProperties = rootProject.file("local.properties")
        val apikeyProperties = Properties()
        apikeyProperties.load(FileInputStream(localProperties))

        buildConfigField("String", "API_KEY", apikeyProperties["apiKey"].toString())
        buildConfigField("String", "API_SECRET", apikeyProperties["readAccessToken"].toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(path =":domain"))
    implementation(project(path = ":data-repository"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.networking.okhttp)
    implementation(libs.networking.retrofit)
    implementation(libs.converter.gson)
    testImplementation(libs.testing.mockito.inline)
    testImplementation(libs.testing.mockito)
    testImplementation(libs.testing.kotlinx.coroutines)
}