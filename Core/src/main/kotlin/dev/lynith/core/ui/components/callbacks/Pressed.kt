package dev.lynith.core.ui.components.callbacks

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.ComponentEvent

class Pressed(
    val mouseX: Int,
    val mouseY: Int,
    val button: Int
) : ComponentEvent() {

    override fun shouldPass(component: Component<*, *>): Boolean {
        val contains = component.bounds.contains(mouseX, mouseY)

        if (contains) {
            component.customProperties["pressed"] = true
        }

        return contains
    }

}