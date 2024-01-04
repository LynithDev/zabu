package dev.lynith.core.hud

import dev.lynith.core.Platform
import dev.lynith.core.hud.HudElement
import dev.lynith.core.ui.callbacks.impl.CursorMoved
import dev.lynith.core.ui.callbacks.impl.Pressed
import dev.lynith.core.ui.callbacks.impl.Released
import dev.lynith.core.ui.components.Screen

class HudConfigScreen : Screen() {

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        Platform.hudManager.render(delta)
    }

    override fun init() {
        var state = HudElement.DraggingState.Stopped

        on<Pressed> {
            if (state == HudElement.DraggingState.Stopped) {
                state = HudElement.DraggingState.Started
                Platform.hudManager.drag(state, it.mouseX.toFloat(), it.mouseY.toFloat())
            }
        }

        on<CursorMoved> {
            if (state != HudElement.DraggingState.Stopped) {
                state = HudElement.DraggingState.Dragging
                Platform.hudManager.drag(state, it.mouseX.toFloat(), it.mouseY.toFloat())
            }
        }

        on<Released> {
            if (state != HudElement.DraggingState.Stopped) {
                state = HudElement.DraggingState.Stopped
                Platform.hudManager.drag(state, it.mouseX.toFloat(), it.mouseY.toFloat())
            }
        }
    }

}