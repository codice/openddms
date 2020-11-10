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
    id("com.diffplug.spotless") version Versions.spotless
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    `maven-publish`
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
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    exclude("**/resources/**")
    exclude("**/build/**")
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/dokka"
    jdkVersion = Versions.dokkaJvmVersion
}

val sourcesJar by tasks.registering(Jar::class) {
    classifier = "sources"
    from(sourceSets.main.get().allSource)
}

publishing {
    repositories {

        var urlString = "http://artifacts.codice.org/content/repositories/releases/"
        if (Versions.project.endsWith("SNAPSHOT")) urlString = "http://artifacts.codice.org/content/repositories/snapshots/"

        maven {
            url = uri(urlString)
            credentials {
                username = project.findProperty("username") as? String
                password = project.findProperty("password") as? String
            }
        }
    }

    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}
