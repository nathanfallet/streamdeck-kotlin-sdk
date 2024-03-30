package me.nathanfallet.streamdeck.plugins

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import me.nathanfallet.streamdeck.events.keyDown.KeyDownEvent
import me.nathanfallet.streamdeck.models.RegisterPlugin
import me.nathanfallet.streamdeck.models.StreamDeckJson
import me.nathanfallet.streamdeck.models.info.Info
import me.nathanfallet.streamdeck.usecases.IHandleEventUseCase

class Plugin(
    val port: Int,
    val pluginUUID: String,
    val registerEvent: String,
    val info: Info,
    val usecases: List<IHandleEventUseCase>,
) {

    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    suspend fun run() {
        client.ws(
            method = HttpMethod.Get,
            host = "localhost",
            port = port,
            path = "/",
        ) {
            send(Frame.Text(StreamDeckJson.json.encodeToString(RegisterPlugin(registerEvent, pluginUUID))))

            for (frame in incoming) {
                if (frame !is Frame.Text) continue
                val payload = frame.readText()
                val event = StreamDeckJson.json.parseToJsonElement(payload).jsonObject["event"]?.jsonPrimitive?.content

                val type = when (event) {
                    "keyDown" -> KeyDownEvent.serializer()
                    else -> continue // Unknown event or not handled
                }
                val data = StreamDeckJson.json.decodeFromString(type, payload)

                for (usecase in usecases) {
                    usecase(data, this@Plugin)
                }
            }
        }
    }

}
