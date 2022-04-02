object Dependencies {

    object AndroidX {
        val core by lazy { "androidx.core:core-ktx:${Versions.Android.coreVersion}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.Android.appCompatVersion}" }
        val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayoutVersion}" }
        val junit by lazy { "androidx.test.ext:junit:${Versions.Android.junitVersion}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.Android.espressoVersion}" }
    }

    object Google {
        val material by lazy { "com.google.android.material:material:${Versions.Google.materialVersion}" }
    }

    val junit by lazy { "junit:junit:${Versions.junitVersion}" }

}
