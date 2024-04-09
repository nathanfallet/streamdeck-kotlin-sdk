package me.nathanfallet.streamdeck.events.dialDown

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DialDownEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: DialDownPayload
) : IEvent
