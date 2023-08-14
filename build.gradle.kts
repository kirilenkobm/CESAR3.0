import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream

plugins {
    kotlin("jvm") version "1.9.0"
    application
}


val gitVersion: String by lazy {
    val result = ByteArrayOutputStream()
    exec {
        commandLine("git", "describe", "--tags", "--abbrev=0")
        standardOutput = result
    }
    result.toString().trim()
}


group = "com.github.kirilenkobm"
version = gitVersion


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("updateReadme") {
    doLast {
        val readmeFile = File("README.md")
        val badgeUrlPattern = """https://img.shields.io/badge/version-([a-zA-Z0-9.-]+)-orange""".toRegex()

        // Replace single '-' with '--' in the version string
        val sanitizedVersion = version.toString().replace("-", "--")

        val newBadgeUrl = "https://img.shields.io/badge/version-$sanitizedVersion-orange"
        val updatedContent = readmeFile.readText().replace(badgeUrlPattern, newBadgeUrl)
        readmeFile.writeText(updatedContent)
    }
}

tasks.register("generateVersionFile") {
    doLast {
        val versionFile = File("src/main/kotlin/Version.kt")
        val content = """
            /**
            * This file is generated automatically.
            */
            object Version {
                const val CURRENT = "$gitVersion"
            }
            
        """.trimIndent()
        versionFile.writeText(content)
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateVersionFile")
}

tasks.named("build") {
    dependsOn("updateReadme")
}

application {
    mainClass.set("MainKt")
}
