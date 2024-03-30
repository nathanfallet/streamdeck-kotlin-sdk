package me.nathanfallet.streamdeck.models.info

import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val id: String,
    val name: String,
    val size: Size,
    val type: Int,
)
