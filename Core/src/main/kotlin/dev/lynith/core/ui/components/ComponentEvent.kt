package dev.lynith.core.ui.components

import dev.lynith.core.events.Event

open class ComponentEvent : Event {

    open fun shouldPass(component: Component<*, *>): Boolean {
        return true
    }

    open fun postPass(component: Component<*, *>) {
        // no-op
    }

}
