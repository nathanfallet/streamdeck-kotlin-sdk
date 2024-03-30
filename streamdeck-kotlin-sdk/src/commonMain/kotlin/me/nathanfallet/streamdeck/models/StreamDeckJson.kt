package me.nathanfallet.streamdeck.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object StreamDeckJson {

    @OptIn(ExperimentalSerializationApi::class)
    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

}
