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

    api(project(":domain"))
    api(project(":data"))

    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.viewModelScope)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.Dagger.hilt)
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")


    kapt(Dependencies.Dagger.hiltCompiler)

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")

    androidTestImplementation(Dependencies.AndroidX.junit)
    androidTestImplementation(Dependencies.AndroidX.espresso)
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

