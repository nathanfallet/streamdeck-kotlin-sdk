package me.nathanfallet.streamdeck.events.sendToPropertyInspector

import kotlinx.serialization.Serializable

@Serializable
data class SendToPropertyInspectorEvent(
    val action: String,
    val event: String,
    val context: String,
    val payload: Map<String, String>,
)
