package me.nathanfallet.streamdeck.events.willDisappear

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class WillDisappearEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: WillDisappearPayload,
) : IEvent
