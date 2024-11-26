plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKsp)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.john.countrylist"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose.core)
    implementation(libs.bundles.io.coil.core)

    implementation(libs.glide)

    implementation(libs.gson.core)

    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.dagger.compiler)
    ksp(libs.hilt.compiler)

    implementation(project(":core"))
    implementation(project(":network"))
    implementation(project(":test-common"))

    testImplementation(libs.junit)
    testImplementation(libs.bundles.kotlin.testing.tools)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}