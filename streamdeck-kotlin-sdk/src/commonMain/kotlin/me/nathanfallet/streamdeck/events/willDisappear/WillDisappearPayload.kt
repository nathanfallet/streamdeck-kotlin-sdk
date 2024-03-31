package me.nathanfallet.streamdeck.events.willDisappear

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class WillDisappearPayload(
    val settings: Map<String, String>,
    val coordinates: Coordinates,
    val controller: String,
    val state: Int?,
    val isInMultiAction: Boolean,
)
