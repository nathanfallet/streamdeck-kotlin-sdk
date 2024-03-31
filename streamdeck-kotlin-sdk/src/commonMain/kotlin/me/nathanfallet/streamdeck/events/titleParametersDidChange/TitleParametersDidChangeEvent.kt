package me.nathanfallet.streamdeck.events.titleParametersDidChange

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class TitleParametersDidChangeEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: TitleParametersDidChangePayload,
) : IEvent
