package me.nathanfallet.streamdeck.events.switchToProfile

import kotlinx.serialization.Serializable

@Serializable
data class SwitchToProfilePayload(
    val profile: String,
    val page: Int,
)
