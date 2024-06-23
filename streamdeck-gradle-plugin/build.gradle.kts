plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.2.1"
}

repositories {
    gradlePluginPortal()
}

group = "me.nathanfallet.streamdeck"
version = "1.2.0"

gradlePlugin {
    website = "https://github.com/nathanfallet/streamdeck-kotlin-sdk"
    vcsUrl = "https://github.com/nathanfallet/streamdeck-kotlin-sdk.git"

    plugins {
        create("streamdeck-gradle-plugin") {
            id = "me.nathanfallet.streamdeck"
            implementationClass = "me.nathanfallet.streamdeck.StreamDeckPlugin"
            displayName = "StreamDeck Gradle Plugin"
            description = "A Gradle plugin to create and bundle StreamDeck plugins"
            tags = listOf("streamdeck", "streamdeck-plugin", "streamdeck-sdk", "kotlin", "sdk")
        }
    }
}
