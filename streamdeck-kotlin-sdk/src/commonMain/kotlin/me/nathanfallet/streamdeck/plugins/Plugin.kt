package me.nathanfallet.streamdeck.plugins

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking
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

abstract class Plugin : CliktCommand(), IPlugin {

    // Command line args

    private val port by option("-port").int().required()
    private val registerEvent by option("-registerEvent").required()

    override val pluginUUID by option("-pluginUUID").required()
    override val info by option("-info").convert { StreamDeckJson.json.decodeFromString<Info>(it) }.required()

    // Websocket session and client

    private var session: DefaultClientWebSocketSession? = null

    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    // Registered usecases

    private val usecases = mutableListOf<IHandleEventUseCase>()

    // Main loop

    abstract fun onEnable()

    final override fun run() = runBlocking {
        session = client.webSocketSession(
            method = HttpMethod.Get,
            host = "localhost",
            port = port,
            path = "/",
        )

        sendPayload(RegisterPlugin(registerEvent, pluginUUID))
        onEnable()

        while (true) {
            val frame = session?.incoming?.receive() as? Frame.Text ?: continue
            handleFrame(frame.readText())
        }
    }

    // Handle incoming frames

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
            try {
                usecase(data, this)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Send payloads

    private suspend inline fun <reified T> sendPayload(payload: T) {
        session?.send(Frame.Text(StreamDeckJson.json.encodeToString(payload)))
    }

    // IPlugin implementation

    override fun registerUseCase(usecase: IHandleEventUseCase) = usecases.add(usecase)

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
