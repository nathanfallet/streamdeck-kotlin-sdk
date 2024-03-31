package me.nathanfallet.streamdeck.events.applicationDidTerminate

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class ApplicationDidTerminateEvent(
    override val event: String,
    val payload: ApplicationDidTerminatePayload,
) : IEvent
