package dev.lynith.core.events.impl

import dev.lynith.core.events.Event

class MinecraftScreenChangedEvent(
    val screen: String
) : Event