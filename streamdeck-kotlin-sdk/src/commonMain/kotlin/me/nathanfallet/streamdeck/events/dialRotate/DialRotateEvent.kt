package me.nathanfallet.streamdeck.events.dialRotate

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DialRotateEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: DialRotatePayload
) : IEvent
