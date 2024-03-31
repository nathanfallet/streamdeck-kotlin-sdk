package me.nathanfallet.streamdeck.events.applicationDidLaunch

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationDidLaunchPayload(
    val application: String,
)
