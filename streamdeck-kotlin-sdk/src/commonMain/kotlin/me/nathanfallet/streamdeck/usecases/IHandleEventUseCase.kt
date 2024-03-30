package me.nathanfallet.streamdeck.usecases

import me.nathanfallet.streamdeck.events.IEvent
import me.nathanfallet.streamdeck.plugins.IPlugin
import me.nathanfallet.usecases.base.IPairSuspendUseCase

interface IHandleEventUseCase : IPairSuspendUseCase<IEvent, IPlugin, Unit>
