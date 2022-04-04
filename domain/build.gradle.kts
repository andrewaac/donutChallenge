plugins {
    id("java-library")
    id("kotlin")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(Dependencies.javaxInject)

    testImplementation(Dependencies.Coroutines.android)
    testImplementation(Dependencies.Coroutines.core)
    testImplementation(Dependencies.Coroutines.test)
    testImplementation(Dependencies.JUnit5.api)
    testImplementation(Dependencies.JUnit5.engine)
    testImplementation(Dependencies.JUnit5.params)
    testImplementation(Dependencies.Mockito.kotlin)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
