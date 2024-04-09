package me.nathanfallet.streamdeck.events.touchTap

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class TouchTapEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: TouchTapPayload
) : IEvent
