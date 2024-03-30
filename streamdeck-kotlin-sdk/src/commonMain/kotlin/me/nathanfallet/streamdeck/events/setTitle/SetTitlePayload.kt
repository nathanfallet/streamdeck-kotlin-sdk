package me.nathanfallet.streamdeck.events.setTitle

import kotlinx.serialization.Serializable

@Serializable
data class SetTitlePayload(
    val title: String,
    val target: Int,
    val state: Int?,
)
