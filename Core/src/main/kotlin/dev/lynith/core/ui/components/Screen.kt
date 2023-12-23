package dev.lynith.core.ui.components

import dev.lynith.core.ClientStartup
import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.bridge.gui.MCScreen
import dev.lynith.core.ui.components.callbacks.Destroyed
import dev.lynith.core.ui.components.callbacks.Pressed
import dev.lynith.core.ui.components.callbacks.Released
import dev.lynith.core.ui.components.features.Children
import dev.lynith.core.ui.styles.ComponentStyles

abstract class Screen : Component<Screen, ComponentStyles.EmptyStyles<Screen>>(), Children {
    override val children: MutableList<Component<*, *>> = ArrayList()
    var shouldPauseGame: Boolean = false

    override fun postRender(mouseX: Int, mouseY: Int, delta: Float) {
        for (child in children) {
            child.render(mouseX, mouseY, delta)
        }

        super.postRender(mouseX, mouseY, delta)
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {

    }

    override fun postInit() {
        super.postInit()

        on<Destroyed> {
            clearChildren()
        }

        for (child in children) {
            child.init()
        }
    }

    override fun emit(event: ComponentEvent) {
        super.emit(event)

        for (child in children) {
            child.emit(event)
        }
    }

    override var styles = ComponentStyles.EmptyStyles(this)

    fun toMCScreen(): MCScreen {
        return WrappedScreen(this)
    }

    private class WrappedScreen(
        private val screen: Screen
    ) : MCScreen() {

        override fun render(mouseX: Int, mouseY: Int, delta: Float) {
            super.render(mouseX, mouseY, delta)
            screen.wrappedRender(mouseX, mouseY, delta)
        }

        override fun init() {
            super.init()
            screen.wrappedInit()
        }

        override fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
            super.mouseClicked(mouseX, mouseY, button)
            screen.emit(Pressed(mouseX, mouseY, button))
        }

        override fun mouseReleased(mouseX: Int, mouseY: Int, button: Int) {
            super.mouseReleased(mouseX, mouseY, button)
            screen.emit(Released(mouseX, mouseY, button))
        }

        override fun shouldPauseGame(): Boolean {
            return screen.shouldPauseGame
        }

        override fun closed() {
            super.closed()
            screen.emit(Destroyed())
        }
    }
}
