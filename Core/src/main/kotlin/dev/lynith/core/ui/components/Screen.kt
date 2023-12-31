package dev.lynith.core.ui.components

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.MCScreen
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.callbacks.*
import dev.lynith.core.ui.callbacks.impl.*
import dev.lynith.core.ui.styles.ComponentWithChildrenStyles

abstract class Screen : ComponentWithChildren<Screen, ComponentWithChildrenStyles.EmptyStyles<Screen>>() {
    var shouldPauseGame: Boolean = false

    override var bounds: BoundingBox = BoundingBox(
        width = Platform.renderer.windowWidth.toFloat(),
        height = Platform.renderer.windowHeight.toFloat()
    )

    init {
        this.screen = this
    }

    override fun preInit() {
        once<Destroyed> {
            unregister()
        }
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        // Empty
    }

    override var styles = ComponentWithChildrenStyles.EmptyStyles(this)

    override fun child(child: Component<*, *>) {
        super.child(child)
        child.screen = this
    }

    // Utils

    fun toMCScreen(): MCScreen {
        return WrappedScreen(this)
    }

    private class WrappedScreen(
        private val screen: Screen
    ) : MCScreen() {
        var prevX: Int? = null
        var prevY: Int? = null

        override fun render(mouseX: Int, mouseY: Int, delta: Float) {
            super.render(mouseX, mouseY, delta)

            val mouseXScaled = mouseX * Platform.renderer.scaleFactor
            val mouseYScaled = mouseY * Platform.renderer.scaleFactor

            if (prevX != mouseXScaled || prevY != mouseYScaled) {
                prevX = mouseXScaled
                prevY = mouseYScaled
                screen.emit(CursorMoved(prevX!!, prevY!!))
            }

            screen.wrappedRender(mouseXScaled, mouseYScaled, delta)
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

        override fun resized() {
            super.resized()

            screen.bounds = BoundingBox(
                width = Platform.renderer.windowWidth.toFloat(),
                height = Platform.renderer.windowHeight.toFloat()
            )

            screen.emit(WindowResized(screen.bounds.width, screen.bounds.height))
        }

        override fun closed() {
            super.closed()
            screen.emit(Destroyed())
        }
    }
}
