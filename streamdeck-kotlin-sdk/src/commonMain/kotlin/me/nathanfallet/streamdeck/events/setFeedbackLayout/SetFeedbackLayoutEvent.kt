package me.nathanfallet.streamdeck.events.setFeedbackLayout

import kotlinx.serialization.Serializable

@Serializable
data class SetFeedbackLayoutEvent(
    val event: String,
    val context: String,
    val payload: SetFeedbackLayoutPayload
)
