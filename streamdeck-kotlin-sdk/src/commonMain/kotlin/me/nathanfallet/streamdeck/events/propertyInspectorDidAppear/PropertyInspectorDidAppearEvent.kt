package me.nathanfallet.streamdeck.events.propertyInspectorDidAppear

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class PropertyInspectorDidAppearEvent(
    val action: String,
    override val event: String,
    val context: String,
    val device: String,
) : IEvent
