package me.nathanfallet.streamdeck.plugins

import me.nathanfallet.streamdeck.models.info.Info
import me.nathanfallet.streamdeck.models.payloads.Destination

interface IPlugin {

    val pluginUUID: String
    val info: Info

    suspend fun openUrl(url: String)
    suspend fun setTitle(context: String, title: String, destination: Destination)

}
