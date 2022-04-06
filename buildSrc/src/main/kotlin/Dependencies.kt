object Dependencies {

    object AndroidX {
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.Android.appCompatVersion}" }
        val constraintLayout by lazy {
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayoutVersion}"
        }
        val core by lazy { "androidx.core:core-ktx:${Versions.Android.coreVersion}" }
        val coreTesting by lazy { "androidx.arch.core:core-testing:${Versions.Android.coreTestingVersion}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.Android.espressoVersion}" }
        val fragment by lazy { "androidx.fragment:fragment-ktx:${Versions.Android.fragmentVersion}" }
        val junit by lazy { "androidx.test.ext:junit:${Versions.Android.junitVersion}" }
        val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.liveDataVersion}" }
        val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.Android.navigationVersion}" }
        val navigationUI by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.Android.navigationVersion}" }
        val testRunner by lazy {"androidx.test:runner:${Versions.Android.testVersion}"}
        val testRules by lazy {"androidx.test:rules:${Versions.Android.testVersion}"}
        val viewModelScope by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.viewModelVersion}" }
    }

    object Coroutines {
        val android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}" }
        val core by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}" }
        val test by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}" }
    }

    object Dagger {
        val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }
    }

    object Google {
        val material by lazy { "com.google.android.material:material:${Versions.Google.materialVersion}" }
    }

    object SquareUp {
        val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}" }
        val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}" }
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    }

    object JUnit5 {
        val api by lazy { "org.junit.jupiter:junit-jupiter-api:${Versions.junit5Version}" }
        val engine by lazy { "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5Version}" }
        val params by lazy { "org.junit.jupiter:junit-jupiter-params:${Versions.junit5Version}" }
    }

    object Mockito {
        val kotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}" }
    }

    val javaxInject by lazy { "javax.inject:javax.inject:1" }
}
