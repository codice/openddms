/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
import com.diffplug.gradle.spotless.FormatExtension
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.dokka") version Versions.dokka
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
    fun FormatExtension.commonFormat(delimiter: String) {
        licenseHeaderFile("codice.license.kt", delimiter)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target(fileTree(projectDir) {
            include("**/src/**/*.kt")
        })
        commonFormat("(package |@file|// Default package)")
        ktlint()
    }

    kotlinGradle {
        target(fileTree(projectDir) {
            include("**/build.gradle.kts")
        })
        commonFormat("(import |plugins )")
        ktlint()
    }
}

detekt {
    input = files("src/main/kotlin", "buildSrc/src/main/kotlin")
    config = files("detekt.yml")
    filters = ".*/resources/.*,.*/build/.*"
}

tasks.withType<DokkaTask> {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"
}
