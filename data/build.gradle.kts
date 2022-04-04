plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

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
}

kapt {
    correctErrorTypes = true
}

dependencies {

    api(project(":domain"))

    api(Dependencies.SquareUp.retrofit)
    api(Dependencies.SquareUp.loggingInterceptor)
    api(Dependencies.SquareUp.gson)

    implementation(Dependencies.Dagger.hilt)

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")

    // Coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")

    kapt(Dependencies.Dagger.hiltCompiler)

    testImplementation("junit:junit:4.13.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
