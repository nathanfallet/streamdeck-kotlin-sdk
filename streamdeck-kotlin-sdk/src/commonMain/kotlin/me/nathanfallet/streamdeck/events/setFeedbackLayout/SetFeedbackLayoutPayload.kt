package me.nathanfallet.streamdeck.events.setFeedbackLayout

import kotlinx.serialization.Serializable

@Serializable
data class SetFeedbackLayoutPayload(
    val layout: String
)
