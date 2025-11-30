package dev.me.extension.event

import net.nonemc.leaf.event.CancelableEvent
import net.nonemc.leaf.event.Event

class ExampleEvent(val config: String) : Event()

class ExampleCancelableEvent(val config: String) : CancelableEvent()