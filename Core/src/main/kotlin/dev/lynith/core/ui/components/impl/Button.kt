package dev.lynith.core.ui.components.impl

import dev.lynith.core.ClientStartup
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.theme.base.ButtonStyles

class Button(
    var text: String = "",

    override var parent: Component<*, *>? = null,
    override var bounds: BoundingBox = BoundingBox(),
) : Component<Button, ButtonStyles>() {

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
                y = y + textHeight(text, styles.fontStyles)
            ),
            fontStyles = styles.fontStyles,
            color = styles.foregroundColor
        )
    }

    override fun init() {

    }

    override var styles = ClientStartup.themeManager.currentTheme.getButtonStyles(this)
}
