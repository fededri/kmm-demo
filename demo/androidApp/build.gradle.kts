plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    //Jetpack compose
    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.0.0"){
        version {
            // TODO: Remove this when Android Studio has become compatible again
            // Android Studio Bumblebee | 2021.1.1 Canary 3 is not compatible with module ui-tooling 1.0.0-rc01 or higher.
            // The Run Configuration for Composable Previews that Android Studio makes expects a PreviewActivity class
            // in the `androidx.compose.ui.tooling.preview` package, but it was moved in 1.0.0-rc01, and thus causes error:
            // "androidx.compose.ui.tooling.preview.PreviewActivity is not an Activity subclass or alias".
            // For more, see: https://stackoverflow.com/questions/68224361/jetpack-compose-cant-preview-after-updating-to-1-0-0-rc01
            strictly("1.0.0")
        }
    }
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")

    //Image loading
    implementation("io.coil-kt:coil-compose:1.3.0")

    // Compose constraint layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")

    // Compose View Binding
    implementation("androidx.compose.ui:ui-viewbinding:1.0.1")

    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.0-rc02")
    // Material Design
    implementation("androidx.compose.material:material:1.0.0-rc02")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.0-rc02")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-rc02")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-rc02")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.0-rc02")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.4")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.fededri.kmmdemo.android"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerVersion = "1.5.10"
        kotlinCompilerExtensionVersion = "1.0.0-rc02"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        exclude( "META-INF/shared_debug.kotlin_module")
    }
}