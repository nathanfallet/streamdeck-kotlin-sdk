package me.nathanfallet.streamdeck.events.keyDown

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.Coordinates

@Serializable
data class KeyDownPayload(
    val settings: Map<String, String>,
    val coordinates: Coordinates,
    val state: Int?,
    val userDesiredState: Int?,
    val isInMultiAction: Boolean,
)
