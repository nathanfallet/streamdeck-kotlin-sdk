package me.nathanfallet.streamdeck.plugins

import me.nathanfallet.streamdeck.models.info.Info
import me.nathanfallet.streamdeck.models.payloads.Destination
import me.nathanfallet.streamdeck.usecases.IHandleEventUseCase

interface IPlugin {

    val pluginUUID: String
    val info: Info

    fun registerUseCase(usecase: IHandleEventUseCase): Boolean

    suspend fun openUrl(url: String)
    suspend fun setTitle(context: String, title: String, destination: Destination)

}
