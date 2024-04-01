package me.nathanfallet.streamdeck.events.showOk

import kotlinx.serialization.Serializable

@Serializable
data class ShowOkEvent(
    val event: String,
    val context: String,
)
