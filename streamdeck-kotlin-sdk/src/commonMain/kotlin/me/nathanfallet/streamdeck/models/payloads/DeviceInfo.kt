package me.nathanfallet.streamdeck.models.payloads

import kotlinx.serialization.Serializable
import me.nathanfallet.streamdeck.models.info.Size

@Serializable
data class DeviceInfo(
    val name: String,
    val size: Size,
    val type: Int,
)
