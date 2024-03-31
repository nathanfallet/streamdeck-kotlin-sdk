package me.nathanfallet.streamdeck.events.propertyInspectorDidDisappear

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class PropertyInspectorDidDisappearEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
) : IEvent
