package me.nathanfallet.streamdeck.plugins

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import me.nathanfallet.streamdeck.events.applicationDidLaunch.ApplicationDidLaunchEvent
import me.nathanfallet.streamdeck.events.applicationDidTerminate.ApplicationDidTerminateEvent
import me.nathanfallet.streamdeck.events.deviceDidConnect.DeviceDidConnectEvent
import me.nathanfallet.streamdeck.events.deviceDidDisconnect.DeviceDidDisconnectEvent
import me.nathanfallet.streamdeck.events.didReceiveDeepLink.DidReceiveDeepLinkEvent
import me.nathanfallet.streamdeck.events.didReceiveGlobalSettings.DidReceiveGlobalSettingsEvent
import me.nathanfallet.streamdeck.events.didReceiveSettings.DidReceiveSettingsEvent
import me.nathanfallet.streamdeck.events.keyDown.KeyDownEvent
import me.nathanfallet.streamdeck.events.keyUp.KeyUpEvent
import me.nathanfallet.streamdeck.events.openUrl.OpenUrlEvent
import me.nathanfallet.streamdeck.events.openUrl.OpenUrlPayload
import me.nathanfallet.streamdeck.events.propertyInspectorDidAppear.PropertyInspectorDidAppearEvent
import me.nathanfallet.streamdeck.events.propertyInspectorDidDisappear.PropertyInspectorDidDisappearEvent
import me.nathanfallet.streamdeck.events.sendToPlugin.SendToPluginEvent
import me.nathanfallet.streamdeck.events.setTitle.SetTitleEvent
import me.nathanfallet.streamdeck.events.setTitle.SetTitlePayload
import me.nathanfallet.streamdeck.events.systemDidWakeUp.SystemDidWakeUpEvent
import me.nathanfallet.streamdeck.events.titleParametersDidChange.TitleParametersDidChangeEvent
import me.nathanfallet.streamdeck.events.willAppear.WillAppearEvent
import me.nathanfallet.streamdeck.events.willDisappear.WillDisappearEvent
import me.nathanfallet.streamdeck.models.RegisterPlugin
import me.nathanfallet.streamdeck.models.info.Info
import me.nathanfallet.streamdeck.models.json.StreamDeckJson
import me.nathanfallet.streamdeck.models.payloads.Destination
import me.nathanfallet.streamdeck.usecases.IHandleEventUseCase

class Plugin(
    val port: Int,
    override val pluginUUID: String,
    val registerEvent: String,
    override val info: Info,
    val usecases: List<IHandleEventUseCase>,
) : IPlugin {

    private var session: DefaultClientWebSocketSession? = null

    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    suspend fun run() {
        session = client.webSocketSession(
            method = HttpMethod.Get,
            host = "localhost",
            port = port,
            path = "/",
        )

        sendPayload(RegisterPlugin(registerEvent, pluginUUID))
        while (true) {
            val frame = session?.incoming?.receive() as? Frame.Text ?: continue
            handleFrame(frame.readText())
        }
    }

    private suspend fun handleFrame(payload: String) {
        val event = StreamDeckJson.json.parseToJsonElement(payload)
            .jsonObject["event"]?.jsonPrimitive?.content ?: return

        val type = when (event) {
            "didReceiveSettings" -> DidReceiveSettingsEvent.serializer()
            "didReceiveGlobalSettings" -> DidReceiveGlobalSettingsEvent.serializer()
            "didReceiveDeepLink" -> DidReceiveDeepLinkEvent.serializer()
            "keyDown" -> KeyDownEvent.serializer()
            "keyUp" -> KeyUpEvent.serializer()
            "willAppear" -> WillAppearEvent.serializer()
            "willDisappear" -> WillDisappearEvent.serializer()
            "titleParametersDidChange" -> TitleParametersDidChangeEvent.serializer()
            "deviceDidConnect" -> DeviceDidConnectEvent.serializer()
            "deviceDidDisconnect" -> DeviceDidDisconnectEvent.serializer()
            "applicationDidLaunch" -> ApplicationDidLaunchEvent.serializer()
            "applicationDidTerminate" -> ApplicationDidTerminateEvent.serializer()
            "systemDidWakeUp" -> SystemDidWakeUpEvent.serializer()
            "propertyInspectorDidAppear" -> PropertyInspectorDidAppearEvent.serializer()
            "propertyInspectorDidDisappear" -> PropertyInspectorDidDisappearEvent.serializer()
            "sendToPlugin" -> SendToPluginEvent.serializer()
            else -> return // Unknown event or not handled
        }
        val data = StreamDeckJson.json.decodeFromString(type, payload)

        for (usecase in usecases) {
            usecase(data, this)
        }
    }

    private suspend inline fun <reified T> sendPayload(payload: T) {
        session?.send(Frame.Text(StreamDeckJson.json.encodeToString(payload)))
    }

    override suspend fun openUrl(url: String) = sendPayload(
        OpenUrlEvent(
            "openUrl",
            OpenUrlPayload(url)
        )
    )

    override suspend fun setTitle(context: String, title: String, destination: Destination) = sendPayload(
        SetTitleEvent(
            "setTitle",
            context,
            SetTitlePayload(title, destination.ordinal, null)
        )
    )

}
