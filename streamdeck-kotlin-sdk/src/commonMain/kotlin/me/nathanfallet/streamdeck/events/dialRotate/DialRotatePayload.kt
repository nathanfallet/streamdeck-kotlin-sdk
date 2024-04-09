package me.nathanfallet.streamdeck.events.dialRotate

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class DialRotatePayload(
    val settings: Map<String, String>,
    val controller: String,
    val coordinates: Coordinates,
    val ticks: Int,
    val pressed: Boolean
)
