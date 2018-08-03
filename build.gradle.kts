import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.60"
    id("com.diffplug.gradle.spotless") version "3.14.0"
}

group = "org.codice.ddms"
version = "1.0-SNAPSHOT"
description = "Library for parsing and creating DoD Discovery Metadata Specification (DDMS) documents."

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.25")
    testImplementation(kotlin("test-junit"))
    testImplementation(group = "org.mockito", name = "mockito-core", version = "2.11.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

spotless {
    java {
        licenseHeaderFile(rootProject.file("codice.license.kt"))
        trimTrailingWhitespace()
        endWithNewline()
        googleJavaFormat()
    }

    kotlin {
        licenseHeaderFile(rootProject.file("codice.license.kt"))
        trimTrailingWhitespace()
        endWithNewline()
        ktlint()
    }

    kotlinGradle {
        trimTrailingWhitespace()
        endWithNewline()
        ktlint()
    }
}
