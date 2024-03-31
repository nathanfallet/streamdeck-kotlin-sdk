package me.nathanfallet.streamdeck.events.keyUp

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class KeyUpPayload(
    val settings: Map<String, String>,
    val coordinates: Coordinates,
    val state: Int?,
    val userDesiredState: Int?,
    val isInMultiAction: Boolean,
)
