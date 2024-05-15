plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}
//

android {
    namespace = "com.blueishincolour.apm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.blueishincolour.apm"
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.junit)


    implementation(libs.androidx.animation.graphics.android)
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.lifecycle.runtime.compose)

    //HILT
    implementation ("com.google.dagger:hilt-android:2.48.1")
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    ksp("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-storage:19.1.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
    implementation("androidx.navigation:navigation-compose:2.7.7")
//    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("io.coil-kt:coil-compose:1.3.2")
    implementation ("io.coil-kt:coil:0.11.0")
    implementation ("io.coil-kt:coil-gif:0.11.0")
    implementation ("io.coil-kt:coil-svg:1.3.2")
    implementation ("androidx.activity:activity-compose:1.6.1") //For rememberLauncherForActivityResult()
    implementation ("androidx.activity:activity-ktx:1.6.1") //For PickVisualMedia contract
//    implementation ("androidx.compose.runtime:runtime-livedata:$1.6.5")
}