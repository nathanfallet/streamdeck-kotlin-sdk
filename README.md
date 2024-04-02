# streamdeck-kotlin-sdk

[![License](https://img.shields.io/github/license/nathanfallet/streamdeck-kotlin-sdk)](LICENSE)
[![Issues](https://img.shields.io/github/issues/nathanfallet/streamdeck-kotlin-sdk)]()
[![Pull Requests](https://img.shields.io/github/issues-pr/nathanfallet/streamdeck-kotlin-sdk)]()
[![Code Size](https://img.shields.io/github/languages/code-size/nathanfallet/streamdeck-kotlin-sdk)]()
[![codecov](https://codecov.io/gh/nathanfallet/streamdeck-kotlin-sdk/graph/badge.svg?token=iIM9xwE4QT)](https://codecov.io/gh/nathanfallet/streamdeck-kotlin-sdk)

A Kotlin SDK to create Stream Deck plugins.

## Install the SDK

Add dependency to your `build.gradle(.kts)` or `pom.xml`:

```kotlin
api("me.nathanfallet.streamdeck:streamdeck-kotlin-sdk:1.1.0")
```

```xml

<dependency>
    <groupId>me.nathanfallet.streamdeck</groupId>
    <artifactId>streamdeck-kotlin-sdk-jvm</artifactId>
    <version>1.1.0</version>
</dependency>
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

For now, you have to build the plugin manually, but a Gradle plugin to do that automatically is planned to be released
soon.

Use `./gradlew assemble` command to build the `build/distributions` folder, unzip the archive and copy the content to
the root of your `.sdPlugin` folder (copy the `bin` and the `lib` folders). Then update path keys in the `manifest.json`
file to match your plugin:

```json
{
  "CodePathMac": "bin/my-awesome-plugin",
  "CodePathWin": "bin/my-awesome-plugin.bat"
}
```
