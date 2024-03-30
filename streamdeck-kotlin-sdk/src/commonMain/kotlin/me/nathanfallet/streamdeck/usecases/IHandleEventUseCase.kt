package me.nathanfallet.streamdeck.usecases

import me.nathanfallet.streamdeck.events.IEvent
import me.nathanfallet.streamdeck.plugins.Plugin
import me.nathanfallet.usecases.base.IPairSuspendUseCase

interface IHandleEventUseCase : IPairSuspendUseCase<IEvent, Plugin, Unit>
