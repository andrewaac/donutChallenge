plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
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
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.liveData)
    implementation(Dependencies.AndroidX.viewModelScope)
    implementation(Dependencies.Dagger.hilt)
    implementation(Dependencies.Google.material)
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.1")

    kapt(Dependencies.Dagger.hiltCompiler)

    testImplementation(Dependencies.Coroutines.test)
    testImplementation(Dependencies.JUnit5.api)
    testImplementation(Dependencies.JUnit5.engine)
    testImplementation(Dependencies.JUnit5.params)
    testImplementation(Dependencies.Mockito.kotlin)

    androidTestImplementation(Dependencies.AndroidX.coreTesting)
    androidTestImplementation(Dependencies.AndroidX.espresso)
    androidTestImplementation(Dependencies.AndroidX.junit)

}

tasks.withType<Test> {
    useJUnitPlatform()
}

