package me.nathanfallet.streamdeck.events.titleParametersDidChange

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.payloads.Coordinates
import me.nathanfallet.streamdeck.models.payloads.TitleParameters

@Serializable
data class TitleParametersDidChangePayload(
    val coordinates: Coordinates,
    val settings: Map<String, String>,
    val state: Int,
    val title: String,
    val titleParameters: TitleParameters,
)
