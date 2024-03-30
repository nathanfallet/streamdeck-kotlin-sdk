package me.nathanfallet.streamdeck.models.info

import kotlinx.serialization.Serializable

@Serializable
data class Size(
    val columns: Int,
    val rows: Int,
)
