package me.nathanfallet.streamdeck.events.willAppear

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class WillAppearEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: WillAppearPayload,
) : IEvent
