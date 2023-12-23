package dev.lynith.core.ui.components.impl

import dev.lynith.core.ClientStartup
import dev.lynith.core.Platform
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.theme.base.LabelStyles

class Label : Component<Label, LabelStyles>() {

    var text: String = ""

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        text(
            text = text,
            bounds = bounds,
            fontStyles = styles.fontStyles,
            color = styles.color
        )
    }

    override fun init() {
        if (text.isNotEmpty() && bounds.width <= 0f) {
            bounds.width = textWidth(text, styles.fontStyles)
            bounds.height = textHeight(text, styles.fontStyles)
        }
    }

    override var styles = Platform.themeManager.currentTheme.getLabelStyles(this)

}