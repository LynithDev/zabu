package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.nvg.Font
import dev.lynith.core.ui.nvg.FontAlign
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.impl.*
import dev.lynith.core.ui.theme.AbstractTheme

open class ButtonStyles @JvmOverloads constructor(
    theme: AbstractTheme,
    state: ComponentStyleState = ComponentStyleState.NORMAL
) : ComponentStyles<Button, ButtonStyles>(
    theme,
    state
) {

    open var innerBoxShadow = Shadow(
        offsetY = 2f,
        radius = CornerRadius(
            value = 4
        ),
        blur = 0f,
        innerColor = Color(255, 255, 255, 0.25f),
        outerColor = Color(255, 255, 255, 0.04f)
    )

    override var padding = Padding(
        top = 8,
        right = 16,
        bottom = 8,
        left = 16
    )

    override var font = FontStyles(
        align = FontAlign.CENTER,
    )

    override var hoverStyles: Class<out ButtonStyles>? = HoveredButtonStyles::class.java

}