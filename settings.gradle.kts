pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "streamdeck-kotlin-sdk"
includeBuild("streamdeck-gradle-plugin")
include(":streamdeck-kotlin-sdk")

include(":sample-plugin")
