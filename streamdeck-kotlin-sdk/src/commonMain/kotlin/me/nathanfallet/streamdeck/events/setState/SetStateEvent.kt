package me.nathanfallet.streamdeck.events.setState

import kotlinx.serialization.Serializable

@Serializable
data class SetStateEvent(
    val event: String,
    val context: String,
    val payload: SetStatePayload,
)
