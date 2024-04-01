package me.nathanfallet.streamdeck.events.setSettings

import kotlinx.serialization.Serializable

@Serializable
data class SetSettingsEvent(
    val event: String,
    val context: String,
    val payload: Map<String, String>,
)
