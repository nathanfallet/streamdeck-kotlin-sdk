package me.nathanfallet.streamdeck.usecases

import dev.kaccelero.usecases.IPairSuspendUseCase
import me.nathanfallet.streamdeck.events.IEvent
import me.nathanfallet.streamdeck.plugins.IPlugin

interface IHandleEventUseCase : IPairSuspendUseCase<IEvent, IPlugin, Unit>
