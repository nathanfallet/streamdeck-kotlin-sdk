package me.nathanfallet.streamdeck.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterPlugin(
    val event: String,
    val uuid: String,
)
