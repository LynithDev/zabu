package dev.lynith.core.ui.callbacks

import dev.lynith.core.events.Event
import dev.lynith.core.ui.components.Component

open class ComponentEvent : Event {

    open fun shouldPass(component: Component<*, *>): Boolean {
        return true
    }

    open fun postPass(component: Component<*, *>) {

    }

}
