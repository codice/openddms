import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    id("com.diffplug.gradle.spotless") version "3.17.0"
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC12"
}

group = "org.codice.ddms"
version = "1.0-SNAPSHOT"
description = "Library for parsing and creating DoD Discovery Metadata Specification (DDMS) documents."

repositories {
    jcenter()
}

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.25")
    testImplementation(kotlin("test-junit"))
    testImplementation(group = "org.mockito", name = "mockito-core", version = "2.24.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

spotless {
    val licenseFile = "codice.license.kt"
    java {
        licenseHeaderFile(licenseFile)
        trimTrailingWhitespace()
        endWithNewline()
        googleJavaFormat()
    }

    kotlin {
        licenseHeaderFile(licenseFile)
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

detekt {
    input = files("src/main/kotlin")
    config = files("detekt.yml")
    filters = ".*/resources/.*,.*/build/.*"
}
