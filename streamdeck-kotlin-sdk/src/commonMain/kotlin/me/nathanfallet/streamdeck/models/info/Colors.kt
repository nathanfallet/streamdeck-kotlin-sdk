package me.nathanfallet.streamdeck.models.info

import kotlinx.serialization.Serializable

@Serializable
data class Colors(
    val buttonPressedBackgroundColor: String,
    val buttonPressedBorderColor: String,
    val buttonPressedTextColor: String,
    val disabledColor: String,
    val highlightColor: String,
    val mouseDownColor: String,
)
