import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = "com.andrewaac.donutchallenge"
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Dagger.hilt)

    kapt(Dependencies.Dagger.hiltCompiler)

    testImplementation(Dependencies.junit)

    androidTestImplementation(Dependencies.AndroidX.junit)
    androidTestImplementation(Dependencies.AndroidX.espresso)
}
