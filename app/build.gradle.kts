plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wirebarley.exchange"
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = "com.wirebarley.exchange"
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(Dependencies.KTX.core)

    implementation(Dependencies.LifeCycle.runtimeKtx)
    implementation(Dependencies.LifeCycle.compose)

    implementation(Dependencies.Dagger.hilt)
    kapt(Dependencies.Dagger.hiltCompiler)

    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.numberPicker)
    implementation(Dependencies.Compose.hilt)

    implementation(Dependencies.Accompanist.systemUiController)

    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.junitAndroidx)
    androidTestImplementation(TestDependencies.espressoCore)
    androidTestImplementation(Dependencies.Compose.uiTestJunit4)

    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.uiTestManifest)
}

kapt {
    correctErrorTypes = true
}
