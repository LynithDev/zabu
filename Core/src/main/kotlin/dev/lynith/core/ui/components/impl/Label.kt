package dev.lynith.core.ui.components.impl

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.ComponentStyles

class Label : Component<Label, ComponentStyles.BaseStyles<Label>>() {

    var text: String = ""

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        text(
            text = text,
            bounds = bounds,
            font = styles.font,
            color = styles.color
        )
    }

    override fun init() {
        if (text.isNotEmpty() && bounds.width <= 0f) {
            bounds.width = textWidth(text, styles.font)
        }

        if (text.isNotEmpty() && bounds.height <= 0f) {
            bounds.height = textHeight(text, styles.font)
        }
    }

}