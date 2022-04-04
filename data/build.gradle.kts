plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

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

    testImplementation(Dependencies.Coroutines.android)
    testImplementation(Dependencies.Coroutines.core)
    testImplementation(Dependencies.Coroutines.test)
    testImplementation(Dependencies.JUnit5.api)
    testImplementation(Dependencies.JUnit5.engine)
    testImplementation(Dependencies.JUnit5.params)
    testImplementation(Dependencies.Mockito.kotlin)

    kapt(Dependencies.Dagger.hiltCompiler)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
