package me.nathanfallet.streamdeck.events.logMessage

import kotlinx.serialization.Serializable

@Serializable
data class LogMessageEvent(
    val event: String,
    val payload: LogMessagePayload,
)
