package me.nathanfallet.streamdeck.events.setImage

import kotlinx.serialization.Serializable

@Serializable
data class SetImageEvent(
    val event: String,
    val context: String,
    val payload: SetImagePayload,
)
