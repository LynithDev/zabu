package dev.lynith.core.ui.callbacks.impl

import dev.lynith.core.ui.callbacks.ComponentEvent
import dev.lynith.core.ui.components.Component

class WindowResized(
    val width: Float,
    val height: Float
) : ComponentEvent() {

    override fun postPass(component: Component<*, *>) {
        println("$component")
        super.postPass(component)
    }

}