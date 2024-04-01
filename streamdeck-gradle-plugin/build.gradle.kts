plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        create("streamdeck-gradle-plugin") {
            id = "streamdeck-gradle-plugin"
            implementationClass = "me.nathanfallet.streamdeck.StreamDeckPlugin"
            displayName = "StreamDeck Gradle Plugin"
            description = "A Gradle plugin to create and bundle StreamDeck plugins"
            tags.set(listOf("streamdeck", "plugin", "gradle", "kotlin", "sdk"))
        }
    }
}
