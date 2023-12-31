package dev.lynith.core.ui.components.impl

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.ComponentStyles

class CustomWidget : Component<CustomWidget, ComponentStyles.BaseStyles<CustomWidget>>() {

    override var styles = ComponentStyles.BaseStyles<CustomWidget>()

    private var onRender: (Renderable) -> Unit = { }
    private var onInit: () -> Unit = {}

    fun onRender(func: (Renderable) -> Unit) {
        onRender = func
    }

    fun onInit(func: () -> Unit) {
        onInit = func
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        onRender.invoke(Renderable(mouseX, mouseY, delta))
    }

    override fun init() {
        onInit.invoke()
    }

    class Renderable(
        val mouseX: Int,
        val mouseY: Int,
        val delta: Float,
    )

}