package dev.lynith.core.events.impl

import dev.lynith.core.events.Event
import dev.lynith.core.input.Key

class KeyPressedEvent(
    val key: Key
) : Event {

}