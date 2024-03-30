package me.nathanfallet.streamdeck.models.info

import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val font: String,
    val language: String,
    val platform: String,
    val platformVersion: String,
    val version: String,
)
