package dev.lynith.core.ui.components.callbacks

import dev.lynith.core.ui.components.ComponentCallback

interface ComponentMouseClicked : ComponentCallback {

    fun invoke(mouseX: Int, mouseY: Int, button: Int)

}
