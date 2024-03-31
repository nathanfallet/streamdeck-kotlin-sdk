package me.nathanfallet.streamdeck.events.didReceiveDeepLink

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.events.IEvent

@Serializable
data class DidReceiveDeepLinkEvent(
    override val event: String,
    val payload: DidReceiveDeepLinkPayload,
) : IEvent
