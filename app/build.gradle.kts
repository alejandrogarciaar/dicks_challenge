plugins {
    kotlin("kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKsp)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.john.dickstest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.john.dickstest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose.core)

    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.dagger.compiler)
    ksp(libs.hilt.compiler)

    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":features:countrylist"))
    implementation(project(":features:countrydetail"))
}