package dev.lynith.core.hud

import dev.lynith.core.config.Category
import dev.lynith.core.config.ConfigOption
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.nvg.NanoVGHelper

abstract class HudElement(
    val name: String,
) : NanoVGHelper() {

    protected abstract fun render(delta: Float)

    @ConfigOption
    open var bounds: BoundingBox = BoundingBox()

    @ConfigOption
    var visible: Boolean = true

    @ConfigOption
    var scaleFactor: Float = 1f

    private var offsetX: Float = 0f
    private var offsetY: Float = 0f

    fun wrappedRender(delta: Float) {
        scale(ctx, scaleFactor, scaleFactor)
        render(delta)
        scale(ctx, 1f, 1f)
    }

    fun drag(state: DraggingState, mouseX: Float, mouseY: Float) {
        when (state) {
            DraggingState.Started -> {
                offsetX = mouseX - bounds.x
                offsetY = mouseY - bounds.y
            }
            DraggingState.Dragging -> {
                bounds.x = mouseX - offsetX
                bounds.y = mouseY - offsetY
            }
            DraggingState.Stopped -> {
                offsetX = 0f
                offsetY = 0f
            }
        }
    }

    enum class DraggingState {
        Started,
        Dragging,
        Stopped
    }

}