package dev.lynith.core.ui.callbacks.impl

import dev.lynith.core.ui.callbacks.ComponentEvent
import dev.lynith.core.ui.components.Component

class Released(
    val mouseX: Int,
    val mouseY: Int,
    val button: Int
) : ComponentEvent() {

    override fun shouldPass(component: Component<*, *>): Boolean {
        return component.bounds.contains(mouseX, mouseY)
    }

    override fun postPass(component: Component<*, *>) {
        if (component.customProperties.contains("pressed") && component.customProperties["pressed"] == true) {
            component.customProperties.remove("pressed")
            component.emit(Clicked(mouseX, mouseY, button))
        }
    }

}
