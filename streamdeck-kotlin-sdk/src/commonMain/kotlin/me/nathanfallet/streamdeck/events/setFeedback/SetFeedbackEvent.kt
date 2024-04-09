package me.nathanfallet.streamdeck.events.setFeedback

import kotlinx.serialization.Serializable

@Serializable
data class SetFeedbackEvent(
    val event: String,
    val context: String,
    val payload: Map<String, String>
)
