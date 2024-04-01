package me.nathanfallet.streamdeck.events.getGlobalSettings

import kotlinx.serialization.Serializable

@Serializable
data class GetGlobalSettingsEvent(
    val event: String,
    val context: String,
)
