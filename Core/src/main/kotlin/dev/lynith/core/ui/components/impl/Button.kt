package dev.lynith.core.ui.components.impl

import dev.lynith.core.ClientStartup
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.theme.base.ButtonStyles

class Button : Component<Button, ButtonStyles>() {
    var text: String = ""

    override fun render(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
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

    override var styles = ClientStartup.instance.themeManager.currentTheme.getButtonStyles(this)
}
