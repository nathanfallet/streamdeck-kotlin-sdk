package me.nathanfallet.streamdeck.events.didReceiveSettings

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DidReceiveSettingsEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
    val payload: DidReceiveSettingsPayload,
) : IEvent
