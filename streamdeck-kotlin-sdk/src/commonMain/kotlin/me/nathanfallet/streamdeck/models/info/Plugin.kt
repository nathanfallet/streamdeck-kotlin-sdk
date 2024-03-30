package me.nathanfallet.streamdeck.models.info

import kotlinx.serialization.Serializable

@Serializable
data class Plugin(
    val uuid: String,
    val version: String,
)
