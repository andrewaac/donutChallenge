plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")

    // Coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")

    // Mockito
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.1.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
