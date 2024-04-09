package me.nathanfallet.streamdeck.events.touchTap

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class TouchTapPayload(
    val settings: Map<String, String>,
    val controller: String,
    val coordinates: Coordinates,
    val tapPos: List<Int>,
    val hold: Boolean
)
