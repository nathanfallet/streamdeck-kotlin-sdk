package me.nathanfallet.streamdeck.events.getSettings

import kotlinx.serialization.Serializable

@Serializable
data class GetSettingsEvent(
    val event: String,
    val context: String,
)
