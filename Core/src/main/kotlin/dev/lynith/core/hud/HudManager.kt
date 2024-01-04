package dev.lynith.core.hud

import dev.lynith.core.Platform
import dev.lynith.core.hud.impl.FPSElement

class HudManager {

    private val elements: MutableList<HudElement> = mutableListOf()
    private val visibleElements: MutableList<HudElement> = mutableListOf()

    private var reorderList: Boolean = false

    fun render(delta: Float) {
        if (reorderList) {
            visibleElements.clear()
            visibleElements.addAll(elements.filter { it.visible })
            reorderList = false
        }

        for (element in visibleElements) {
            element.wrappedRender(delta)
        }
    }

    private var currentDraggingElement: HudElement? = null
    fun drag(state: HudElement.DraggingState, mouseX: Float, mouseY: Float) {
        if (currentDraggingElement == null) {
            currentDraggingElement = visibleElements.firstOrNull {
                it.bounds.contains(mouseX, mouseY)
            }
        }

        if (currentDraggingElement != null) {
            currentDraggingElement!!.drag(state, mouseX, mouseY)

            if (state == HudElement.DraggingState.Stopped) {
                currentDraggingElement = null
            }
        }
    }

    init {
        add(FPSElement())
    }

    fun add(element: HudElement) {
        Platform.config.register(element)
        elements.add(element)
        Platform.config.loadIntoClass(element)
        reorderList = true
    }

}