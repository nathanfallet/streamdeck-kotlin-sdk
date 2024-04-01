package me.nathanfallet.streamdeck.events.setGlobalSettings

import kotlinx.serialization.Serializable

@Serializable
data class SetGlobalSettingsEvent(
    val event: String,
    val context: String,
    val payload: Map<String, String>,
)
