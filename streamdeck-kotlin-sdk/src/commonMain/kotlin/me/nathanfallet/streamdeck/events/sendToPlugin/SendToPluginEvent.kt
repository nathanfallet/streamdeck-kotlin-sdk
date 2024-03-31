package me.nathanfallet.streamdeck.events.sendToPlugin

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class SendToPluginEvent(
    val action: String,
    override val event: String,
    val context: String,
    val payload: Map<String, String>,
) : IEvent
