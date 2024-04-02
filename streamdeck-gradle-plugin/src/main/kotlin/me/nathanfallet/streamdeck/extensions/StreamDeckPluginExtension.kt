package me.nathanfallet.streamdeck.extensions

import org.gradle.api.provider.Property

interface StreamDeckPluginExtension {

    val pluginId: Property<String>

}
