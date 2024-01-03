package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.nvg.HorizontalAlign
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

    override var boxShadow = Shadow(
        offsetY = 2f,
        radius = CornerRadius(
            value = 4
        ),
        blur = 0f,
        innerColor = Color(27, 31, 35, 0.04f),
        outerColor = Color(27, 31, 35, 0f)
    )

    override var border = Border(
        thickness = 2f,
        color = theme.colorScheme.border
    )

    override var borderRadius = CornerRadius(
        value = 4
    )

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
        top = 6,
        right = 16,
        bottom = 6,
        left = 16
    )

    override var font = FontStyles(
        horizontalAlign = HorizontalAlign.CENTER,
    )

    override var hoverStyles: Class<out ButtonStyles>? = HoveredButtonStyles::class.java

    class HoveredButtonStyles @JvmOverloads constructor(
        theme: AbstractTheme,
        state: ComponentStyleState = ComponentStyleState.HOVER
    ) : ButtonStyles(
        theme,
        state
    ) {

        override var backgroundColor = theme.colorScheme.backgroundHover

        override var hoverStyles: Class<out ButtonStyles>? = this.javaClass

    }

}