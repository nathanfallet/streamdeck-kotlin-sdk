package me.nathanfallet.streamdeck.events.keyUp

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class KeyUpEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: KeyUpPayload,
) : IEvent
