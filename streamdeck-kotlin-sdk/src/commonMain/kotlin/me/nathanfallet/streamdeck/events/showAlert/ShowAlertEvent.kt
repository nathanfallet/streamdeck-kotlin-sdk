package me.nathanfallet.streamdeck.events.showAlert

import kotlinx.serialization.Serializable

@Serializable
data class ShowAlertEvent(
    val event: String,
    val context: String,
)
