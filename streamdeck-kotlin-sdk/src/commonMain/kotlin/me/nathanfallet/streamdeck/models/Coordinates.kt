package me.nathanfallet.streamdeck.models

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val column: Int,
    val row: Int,
)
