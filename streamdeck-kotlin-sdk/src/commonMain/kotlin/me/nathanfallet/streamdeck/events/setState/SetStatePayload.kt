package me.nathanfallet.streamdeck.events.setState

import kotlinx.serialization.Serializable

@Serializable
data class SetStatePayload(
    val state: Int,
)
