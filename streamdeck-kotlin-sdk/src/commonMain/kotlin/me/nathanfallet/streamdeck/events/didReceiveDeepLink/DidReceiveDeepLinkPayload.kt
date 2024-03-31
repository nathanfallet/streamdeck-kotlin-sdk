package me.nathanfallet.streamdeck.events.didReceiveDeepLink

import kotlinx.serialization.Serializable

@Serializable
data class DidReceiveDeepLinkPayload(
    val url: String,
)
