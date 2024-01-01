package dev.lynith.core.ui

abstract class MinecraftScreen {


    abstract fun render(mouseX: Int, mouseY: Int, delta: Float)
    abstract fun init()
    fun closed() {}
    fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {}
    fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {}
    fun mouseClickedMoved(mouseX: Int, mouseY: Int, button: Int, deltaX: Double, deltaY: Double) {}
    fun keyPressed(typedChar: Char, keyCode: Int) {}
}
