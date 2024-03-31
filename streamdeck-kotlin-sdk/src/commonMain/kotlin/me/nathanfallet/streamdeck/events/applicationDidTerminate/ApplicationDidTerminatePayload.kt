package me.nathanfallet.streamdeck.events.applicationDidTerminate

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationDidTerminatePayload(
    val application: String,
)
