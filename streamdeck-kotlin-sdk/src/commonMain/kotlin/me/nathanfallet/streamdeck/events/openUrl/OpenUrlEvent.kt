package me.nathanfallet.streamdeck.events.openUrl

import kotlinx.serialization.Serializable

@Serializable
data class OpenUrlEvent(
    val event: String,
    val payload: OpenUrlPayload,
)
