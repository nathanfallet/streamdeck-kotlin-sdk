package me.nathanfallet.streamdeck.events.switchToProfile

import kotlinx.serialization.Serializable

@Serializable
data class SwitchToProfileEvent(
    val event: String,
    val context: String,
    val device: String,
    val payload: SwitchToProfilePayload,
)
