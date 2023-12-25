package dev.lynith.core.ui.callbacks.impl

import dev.lynith.core.ui.callbacks.ComponentEvent

class CursorMoved(
    val mouseX: Int,
    val mouseY: Int,
) : ComponentEvent() {
    
}