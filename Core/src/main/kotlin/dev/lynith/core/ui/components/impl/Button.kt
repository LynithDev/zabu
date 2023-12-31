package dev.lynith.core.ui.components.impl

import dev.lynith.core.Platform
import dev.lynith.core.ui.callbacks.impl.CursorMoved
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.theme.base.ButtonStyles

class Button : Component<Button, ButtonStyles>() {
    var text: String = ""

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        boxShadow(bounds, styles.boxShadow)

        rectangle(
            bounds = bounds,
            color = if (mouseOver) styles.hoveredBackgroundColor else styles.backgroundColor,
            border = styles.border,
            cornerRadius = styles.cornerRadius
        )

        boxShadow(bounds, styles.innerBoxShadow)

        text(
            text = text,
            bounds = bounds.with(
                y = bounds.y + (bounds.height / 2) - (textHeight(text, styles.fontStyles) / 2)
            ),
            fontStyles = styles.fontStyles,
            color = if (mouseOver) styles.hoveredForegroundColor else styles.foregroundColor
        )
    }

    override fun init() {
        if (bounds.width <= 0f) {
            bounds.width = textWidth(text, styles.fontStyles) + styles.padding.left + styles.padding.right
        }

        if (bounds.height <= 0f) {
            bounds.height = styles.fontStyles.size + styles.padding.top + styles.padding.bottom
        }
    }

    override var styles: ButtonStyles = Platform.themeManager.currentTheme.getButtonStyles(this)
}
