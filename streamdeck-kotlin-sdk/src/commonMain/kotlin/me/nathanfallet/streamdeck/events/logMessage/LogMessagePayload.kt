package me.nathanfallet.streamdeck.events.logMessage

import kotlinx.serialization.Serializable

@Serializable
data class LogMessagePayload(
    val message: String,
)
