package dev.lynith.core.ui.components.callbacks

import dev.lynith.core.ui.components.ComponentEvent

class CursorMoved(
    val mouseX: Int,
    val mouseY: Int,
) : ComponentEvent() {
    
}