package me.nathanfallet.streamdeck.events.deviceDidDisconnect

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DeviceDidDisconnectEvent(
    override val event: String,
    val device: String,
) : IEvent
