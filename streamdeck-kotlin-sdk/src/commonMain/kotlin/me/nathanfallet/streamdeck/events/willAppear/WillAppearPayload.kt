package me.nathanfallet.streamdeck.events.willAppear

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates

@Serializable
data class WillAppearPayload(
    val settings: Map<String, String>,
    val coordinates: Coordinates,
    val controller: String,
    val state: Int?,
    val isInMultiAction: Boolean,
)
