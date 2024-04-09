package me.nathanfallet.streamdeck.events.setTriggerDescription

import kotlinx.serialization.Serializable

@Serializable
data class SetTriggerDescriptionPayload(
    val rotate: String?,
    val push: String?,
    val touch: String?,
    val longTouch: String?,
)
