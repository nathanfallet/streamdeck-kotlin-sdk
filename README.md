# streamdeck-kotlin-sdk

[![License](https://img.shields.io/github/license/nathanfallet/streamdeck-kotlin-sdk)](LICENSE)
[![Issues](https://img.shields.io/github/issues/nathanfallet/streamdeck-kotlin-sdk)]()
[![Pull Requests](https://img.shields.io/github/issues-pr/nathanfallet/streamdeck-kotlin-sdk)]()
[![Code Size](https://img.shields.io/github/languages/code-size/nathanfallet/streamdeck-kotlin-sdk)]()
[![codecov](https://codecov.io/gh/nathanfallet/streamdeck-kotlin-sdk/graph/badge.svg?token=iIM9xwE4QT)](https://codecov.io/gh/nathanfallet/streamdeck-kotlin-sdk)

A Kotlin SDK to create Stream Deck plugins.

## Install the SDK and the Gradle plugin

Add the gradle plugin and the dependency to your `build.gradle(.kts)`:

```kotlin
plugins {
    id("me.nathanfallet.streamdeck") version "1.2.0"
}

dependencies {
    implementation("me.nathanfallet.streamdeck:streamdeck-kotlin-sdk:1.2.0")
}
```

## Usage

### Create a plugin

Create a plugin is really simple, you just need to extend the `Plugin` class:

```kotlin
class MyAwesomePlugin : Plugin() {

    override fun onEnable() {
        // Setup your plugin here
    }

}
```

And add a `main` function to your application to start it:

```kotlin
fun main(args: Array<String>) = MyAwesomePlugin().main(args)
```

Update your `build.gradle(.kts)` to register your plugin id and main class:

```kotlin
application {
    mainClass = "me.nathanfallet.myawesomeplugin.MyAwesomePluginKt"
}

streamDeckPlugin {
    pluginId = "me.nathanfallet.myawesomeplugin"
}
```

### Handle events

To handle events, create a [usecase](https://github.com/nathanfallet/usecases) that implements `IHandleEventUseCase`.

There are two ways to handle events:

- Have one usecase per event type
- Have one usecase that routes events to other usecases

#### One usecase per event type

```kotlin
class HandleKeyDownUseCase : IHandleEventUseCase {

    override suspend fun invoke(input1: IEvent, input2: IPlugin) {
        if (input1 !is KeyDownEvent) return

        println("Key down event received for key ${input1.payload.coordinates.column}x${input1.payload.coordinates.row}")
    }

}
```

And register it in your plugin:

```kotlin
class MyAwesomePlugin : Plugin() {

    override fun onEnable() {
        // Setup your plugin here

        registerUseCase(HandleKeyDownUseCase()) // Add this
    }

}
```

#### One usecase that routes events to other usecases

```kotlin
class HandleEventsUseCase(
    private val handleKeyDownUseCase: HandleKeyDownUseCase,
    // Add other usecases here
) : IHandleEventUseCase {

    override suspend fun invoke(input1: IEvent, input2: IPlugin) {
        when (input1) {
            is KeyDownEvent -> handleKeyDownUseCase(input1, input2)
            else -> return // Ignore other events
        }
    }

}
```

```kotlin
class HandleKeyDownUseCase : IPairSuspendUseCase<KeyDownEvent, IPlugin> {

    override suspend fun invoke(input1: KeyDownEvent, input2: IPlugin) {
        println("Key down event received for key ${input1.payload.coordinates.column}x${input1.payload.coordinates.row}")
    }

}
```

And register it in your plugin:

```kotlin
class MyAwesomePlugin : Plugin() {

    override fun onEnable() {
        // Setup your plugin here

        registerUseCase(
            HandleEventsUseCase(
                HandleKeyDownUseCase(),
                // Add other usecases here
            )
        )
    }

}
```

### Build the plugin

Create a `manifest.json` file in your `src/main/resources` folder (along with any other assets you want to include in
the plugin, like action icons).

Thanks to the gradle plugin, you can use the `./gradlew buildStreamDeckPlugin` command (or the `buildStreamDeckPlugin`
task from your IDE) to build the `build/<pluginId>.sdPlugin` folder containing the plugin.

You might also need to adjust the manifest file first for it to match with your gradle project name:

```json
{
  "CodePathMac": "bin/my-awesome-plugin",
  "CodePathWin": "bin/my-awesome-plugin.bat"
}
```
