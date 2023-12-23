package dev.lynith.core.ui.components.impl

import dev.lynith.core.Platform
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.theme.base.ButtonStyles
import org.lwjgl.nanovg.NanoVG

class Button : Component<Button, ButtonStyles>() {
    var text: String = ""

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        rectangle(
            bounds = bounds,
            color = styles.backgroundColor,
            border = styles.border,
            cornerRadius = styles.cornerRadius
        )

        text(
            text = text,
            bounds = bounds.with(
                y = bounds.y + (bounds.height / 2) - (styles.fontStyles.size / 2)
            ),
            fontStyles = styles.fontStyles,
            color = styles.foregroundColor
        )
    }

    override fun init() {

    }

    override var styles = Platform.themeManager.currentTheme.getButtonStyles(this)
}
