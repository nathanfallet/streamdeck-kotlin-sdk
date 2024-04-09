package me.nathanfallet.streamdeck.events.dialDown

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class DialDownPayload(
    val controller: String,
    val settings: Map<String, String>,
    val coordinates: Coordinates,
)
