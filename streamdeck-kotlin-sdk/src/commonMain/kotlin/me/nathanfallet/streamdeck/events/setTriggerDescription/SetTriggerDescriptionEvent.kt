package me.nathanfallet.streamdeck.events.setTriggerDescription

import kotlinx.serialization.Serializable

@Serializable
data class SetTriggerDescriptionEvent(
    val event: String,
    val context: String,
    val payload: SetTriggerDescriptionPayload
)
