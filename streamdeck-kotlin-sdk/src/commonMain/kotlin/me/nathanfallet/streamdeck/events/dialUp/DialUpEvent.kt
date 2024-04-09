package me.nathanfallet.streamdeck.events.dialUp

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DialUpEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: DialUpPayload
) : IEvent
