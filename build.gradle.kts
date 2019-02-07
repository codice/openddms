/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("com.diffplug.gradle.spotless") version Versions.spotless
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
}

group = "org.codice.ddms"
version = Versions.project
description = "Library for parsing and creating DoD Discovery Metadata Specification (DDMS) documents."

repositories {
    jcenter()
}

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))

    implementation(Libs.slf4j)
    testImplementation(Libs.mockito)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = Versions.javaTarget
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
        target(fileTree(projectDir) {
            include("**/src/**/*.kt")
        })
        licenseHeaderFile(licenseFile, "(package |@file|// Default package)")
        trimTrailingWhitespace()
        endWithNewline()
        ktlint()
    }

    kotlinGradle {
        target(fileTree(projectDir) {
            include("**/build.gradle.kts")
        })
        licenseHeaderFile(licenseFile, "(import |plugins )")
        trimTrailingWhitespace()
        endWithNewline()
        ktlint()
    }
}

detekt {
    input = files("src/main/kotlin", "buildSrc/src/main/kotlin")
    config = files("detekt.yml")
    filters = ".*/resources/.*,.*/build/.*"
}
