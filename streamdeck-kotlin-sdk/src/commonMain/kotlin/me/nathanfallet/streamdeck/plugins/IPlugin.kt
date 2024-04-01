package me.nathanfallet.streamdeck.plugins

import me.nathanfallet.streamdeck.models.info.Info
import me.nathanfallet.streamdeck.models.payloads.Destination
import me.nathanfallet.streamdeck.usecases.IHandleEventUseCase

interface IPlugin {

    val pluginUUID: String
    val info: Info

    fun registerUseCase(usecase: IHandleEventUseCase): Boolean

    suspend fun setSettings(context: String, settings: Map<String, String>)
    suspend fun getSettings(context: String)
    suspend fun setGlobalSettings(settings: Map<String, String>)
    suspend fun getGlobalSettings()
    suspend fun openUrl(url: String)
    suspend fun logMessage(message: String)
    suspend fun setTitle(context: String, title: String, destination: Destination)
    suspend fun setImage(context: String, image: String, destination: Destination)
    suspend fun showAlert(context: String)
    suspend fun showOk(context: String)
    suspend fun setState(context: String, state: Int)
    suspend fun switchToProfile(device: String, profile: String, page: Int)
    suspend fun sendToPropertyInspector(action: String, context: String, payload: Map<String, String>)

}
