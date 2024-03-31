package me.nathanfallet.streamdeck.events.applicationDidLaunch

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class ApplicationDidLaunchEvent(
    override val event: String,
    val payload: ApplicationDidLaunchPayload,
) : IEvent
