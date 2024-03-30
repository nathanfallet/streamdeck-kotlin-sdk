package me.nathanfallet.streamdeck.models.info

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val application: Application,
    val plugin: Plugin,
    val devicePixelRatio: Int,
    val colors: Colors,
    val devices: List<Device>,
)
