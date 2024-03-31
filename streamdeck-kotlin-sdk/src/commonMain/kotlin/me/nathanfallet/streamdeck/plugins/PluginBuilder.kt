package me.nathanfallet.streamdeck.plugins

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int
import kotlinx.coroutines.runBlocking
import me.nathanfallet.streamdeck.models.json.StreamDeckJson
import me.nathanfallet.streamdeck.usecases.IHandleEventUseCase

class PluginBuilder(
    builder: PluginBuilder.() -> Unit = {},
) : CliktCommand() {

    val port by option("-port").int().required()
    val pluginUUID by option("-pluginUUID").required()
    val registerEvent by option("-registerEvent").required()
    val info by option("-info").required()

    val usecases = mutableListOf<IHandleEventUseCase>()

    init {
        builder()
    }

    override fun run() = runBlocking {
        Plugin(
            port,
            pluginUUID,
            registerEvent,
            StreamDeckJson.json.decodeFromString(info),
            this@PluginBuilder.usecases,
        ).run()
    }

    fun usecase(usecase: IHandleEventUseCase) {
        usecases.add(usecase)
    }

}
