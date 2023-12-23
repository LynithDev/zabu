package dev.lynith.core.ui.components.impl

import dev.lynith.core.ClientStartup
import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.theme.base.LabelStyles

class Label : Component<Label, LabelStyles>() {

    var text: String = ""

    override fun render(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
        text(
            text = text,
            bounds = bounds,
            fontStyles = styles.fontStyles,
            color = styles.color
        )
    }

    override fun init() {

    }

    override var styles = ClientStartup.instance.themeManager.currentTheme.getLabelStyles(this)

}