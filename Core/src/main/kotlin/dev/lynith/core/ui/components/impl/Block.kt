package dev.lynith.core.ui.components.impl

import dev.lynith.core.ui.components.ComponentWithChildren
import dev.lynith.core.ui.styles.ComponentStyles

class Block : ComponentWithChildren<Block, ComponentStyles.BaseStyles<Block>>() {

    override var styles = ComponentStyles.BaseStyles<Block>()

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {

    }

    override fun init() {

    }

    override fun postInit() {
        super.postInit()

        if (bounds.width <= 0f && children.isNotEmpty()) {
            if (layout.properties.direction.isHorizontal()) {
                val totalWidth = children.map { it.bounds.width }.sum()
                bounds.width = totalWidth + (children.size - 1) * (styles.padding.left + styles.padding.right)
            } else {
                bounds.width = children.maxOfOrNull {
                    it.bounds.width
                } ?: 0f
            }

            bounds.width += styles.padding.left + styles.padding.right + (layout.properties.gap.x * (children.size - 1))
        }

        if (bounds.height <= 0f && children.isNotEmpty()) {
            if (layout.properties.direction.isVertical()) {
                val totalHeight = children.map { it.bounds.height }.sum()
                bounds.height = totalHeight + (children.size - 1) * (styles.padding.top + styles.padding.bottom)
            } else {
                bounds.height = children.maxOfOrNull {
                    it.bounds.height
                } ?: 0f
            }

            bounds.height += styles.padding.top + styles.padding.bottom + (layout.properties.gap.y * (children.size - 1))
        }
    }

}