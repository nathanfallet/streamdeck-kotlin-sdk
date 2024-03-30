package me.nathanfallet.streamdeck.events.keyDown

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class KeyDownEvent(
    val action: String,
    val event: String,
    val context: String,
    val device: String,
    val payload: KeyDownPayload,
) : IEvent
