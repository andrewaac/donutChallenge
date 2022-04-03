object Dependencies {

    object AndroidX {
        val core by lazy { "androidx.core:core-ktx:${Versions.Android.coreVersion}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.Android.appCompatVersion}" }
        val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayoutVersion}" }
        val junit by lazy { "androidx.test.ext:junit:${Versions.Android.junitVersion}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.Android.espressoVersion}" }
        val viewModelScope by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0" }
    }

    object Dagger {
        val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }
    }

    object Google {
        val material by lazy { "com.google.android.material:material:${Versions.Google.materialVersion}" }
    }

    object SquareUp {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
        val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}" }
        val gson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}" }
    }

    val junit by lazy { "junit:junit:${Versions.junitVersion}" }

    val javaxInject by lazy { "javax.inject:javax.inject:1" }

}
