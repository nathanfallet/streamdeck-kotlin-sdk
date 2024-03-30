package me.nathanfallet.streamdeck.events.openUrl

import kotlinx.serialization.Serializable

@Serializable
data class OpenUrlPayload(
    val url: String,
)
