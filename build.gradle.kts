plugins {
    kotlin("jvm") version "2.1.0" // Or your current version
}

group = "com.rabahi"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotest dependencies
    testImplementation("io.kotest:kotest-runner-junit5:5.9.0")
    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}