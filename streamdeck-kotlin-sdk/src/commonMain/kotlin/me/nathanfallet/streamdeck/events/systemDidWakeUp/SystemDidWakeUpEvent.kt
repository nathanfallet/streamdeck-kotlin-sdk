package me.nathanfallet.streamdeck.events.systemDidWakeUp

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class SystemDidWakeUpEvent(
    override val event: String,
) : IEvent
