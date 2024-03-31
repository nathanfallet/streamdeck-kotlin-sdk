package me.nathanfallet.streamdeck.models.payloads

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val column: Int,
    val row: Int,
)
