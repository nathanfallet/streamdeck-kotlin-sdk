package me.nathanfallet.streamdeck.events.setImage

import kotlinx.serialization.Serializable

@Serializable
data class SetImagePayload(
    val image: String,
    val target: Int,
    val state: Int?,
)
