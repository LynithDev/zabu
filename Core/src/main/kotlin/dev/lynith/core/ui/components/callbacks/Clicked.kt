package dev.lynith.core.ui.components.callbacks

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.ComponentEvent

class Clicked(
    val mouseX: Int,
    val mouseY: Int,
    val button: Int,
) : ComponentEvent() {

    override fun shouldPass(component: Component<*, *>): Boolean {
        return component.bounds.contains(mouseX, mouseY)
    }

}
