package dev.lynith.core.ui.components

import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.MCScreen
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.callbacks.*
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.ComponentWithChildrenStyles
import java.util.concurrent.CopyOnWriteArrayList

abstract class Screen : ComponentWithChildren<Screen, ComponentWithChildrenStyles.EmptyStyles<Screen>>() {
    var shouldPauseGame: Boolean = false

    override var bounds: BoundingBox = BoundingBox(
        width = Platform.renderer.windowWidth.toFloat(),
        height = Platform.renderer.windowHeight.toFloat()
    )

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        // Empty
    }

    override var styles = ComponentWithChildrenStyles.EmptyStyles(this)

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

            if (prevX != mouseX || prevY != mouseY) {
                prevX = mouseX
                prevY = mouseY
                screen.emit(CursorMoved(mouseX, mouseY))
            }

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
