package dev.lynith.core.ui.components.impl

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.theme.base.ButtonStyles

class Button : Component<Button, ButtonStyles>() {
    var text: String = ""

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        boxShadow(bounds, styles.boxShadow)

        rectangle(
            bounds = bounds,
            color = styles.backgroundColor,
            border = styles.border,
            cornerRadius = styles.borderRadius
        )

        boxShadow(bounds, styles.innerBoxShadow)

        text(
            text = text,
            bounds = bounds.with(
                y = bounds.y + (bounds.height / 2) - (textHeight(text, styles.font) / 2)
            ),
            font = styles.font,
            color = styles.color
        )
    }

    override fun init() {
        if (bounds.width <= 0f) {
            bounds.width = textWidth(text, styles.font) + styles.padding.left + styles.padding.right
        }

        if (bounds.height <= 0f) {
            bounds.height = styles.font.size + styles.padding.top + styles.padding.bottom
        }
    }
}
