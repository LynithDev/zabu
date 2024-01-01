package dev.lynith.core.ui.callbacks.impl

import dev.lynith.core.ui.callbacks.ComponentEvent
import dev.lynith.core.ui.components.Component

class CursorEnter(
    val mouseX: Int,
    val mouseY: Int
) : ComponentEvent() {

    override fun shouldPass(component: Component<*, *>): Boolean {
        return component.bounds.contains(mouseX, mouseY) && !component.cursorInside
    }

}