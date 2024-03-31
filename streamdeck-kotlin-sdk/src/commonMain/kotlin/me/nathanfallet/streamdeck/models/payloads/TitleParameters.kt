package me.nathanfallet.streamdeck.models.payloads

import kotlinx.serialization.Serializable

@Serializable
data class TitleParameters(
    val fontFamily: String,
    val fontSize: Int,
    val fontStyle: String,
    val fontUnderline: Boolean,
    val showTitle: Boolean,
    val titleAlignment: String,
    val titleColor: String,
)
