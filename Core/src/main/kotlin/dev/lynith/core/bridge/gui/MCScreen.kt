package dev.lynith.core.bridge.gui

open class MCScreen {

    open fun render(mouseX: Int, mouseY: Int, delta: Float) {}
    open fun init() {}

    open fun keyPressed(typedChar: Char, keyCode: Int) {}
    open fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {}
    open fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {}
    open fun mouseDragged(mouseX: Int, mouseY: Int, button: Int, time: Float) {}

    open fun closed() {}
    open fun resized() {}

    open fun shouldPauseGame(): Boolean {
        return false
    }
}
