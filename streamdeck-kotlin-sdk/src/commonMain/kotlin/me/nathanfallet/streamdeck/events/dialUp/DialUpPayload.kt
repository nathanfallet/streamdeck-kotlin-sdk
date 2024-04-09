package me.nathanfallet.streamdeck.events.dialUp

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class DialUpPayload(
    val controller: String,
    val settings: Map<String, String>,
    val coordinates: Coordinates,
)
