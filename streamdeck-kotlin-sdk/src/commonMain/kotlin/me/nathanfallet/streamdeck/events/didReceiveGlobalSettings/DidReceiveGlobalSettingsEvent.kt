package me.nathanfallet.streamdeck.events.didReceiveGlobalSettings

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DidReceiveGlobalSettingsEvent(
    override val event: String,
    val payload: DidReceiveGlobalSettingsPayload,
) : IEvent
