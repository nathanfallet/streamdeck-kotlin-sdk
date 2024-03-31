package me.nathanfallet.streamdeck.events.deviceDidConnect

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent
import me.nathanfallet.streamdeck.models.payloads.DeviceInfo

@Serializable
data class DeviceDidConnectEvent(
    override val event: String,
    val device: String,
    val deviceInfo: DeviceInfo,
) : IEvent
