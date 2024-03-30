package me.nathanfallet.streamdeck.events.setTitle

import kotlinx.serialization.Serializable

@Serializable
data class SetTitleEvent(
    val event: String,
    val context: String,
    val payload: SetTitlePayload,
)
