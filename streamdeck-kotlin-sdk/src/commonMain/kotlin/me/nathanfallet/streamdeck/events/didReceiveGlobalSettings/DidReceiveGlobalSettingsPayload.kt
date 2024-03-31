package me.nathanfallet.streamdeck.events.didReceiveGlobalSettings

import kotlinx.serialization.Serializable

@Serializable
data class DidReceiveGlobalSettingsPayload(
    val settings: Map<String, String>,
)
