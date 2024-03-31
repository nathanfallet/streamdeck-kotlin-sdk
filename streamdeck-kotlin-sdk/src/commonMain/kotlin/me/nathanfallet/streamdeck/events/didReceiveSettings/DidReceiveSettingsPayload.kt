package me.nathanfallet.streamdeck.events.didReceiveSettings

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class DidReceiveSettingsPayload(
    val settings: Map<String, String>,
    val coordinates: Coordinates,
    val isInMultiAction: Boolean,
)
