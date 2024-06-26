plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.yasmineraafat.mawakeet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yasmineraafat.mawakeet"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding=true
        dataBinding=true
    }
    dataBinding {
        android.buildFeatures.dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
// LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
// Lifecycles only (without ViewModel or LiveData)
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

// Saved state module for ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")

// Jetpack Compose Integration for ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01")

// Annotation processor
    kapt ("androidx.lifecycle:lifecycle-compiler:2.7.0")
// alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.7.0")

    implementation("androidx.databinding:databinding-runtime:8.4.0")
    implementation("androidx.core:core-ktx:+")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")

    implementation("androidx.fragment:fragment-ktx:1.7.0")

    implementation ("io.reactivex.rxjava2:rxandroid:2.1.0")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.10")
    kapt ("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.2.1")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.4.0")

    implementation ("app.cash.inject:inflation-inject:1.0.1")
    annotationProcessor ("app.cash.inject:inflation-inject-processor:1.0.1")

    // Room with RXjava2 & Kotlin
    implementation( "androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-rxjava2:2.6.1")

   // implementation ("androidx.databinding:databinding-compiler:8.4.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}