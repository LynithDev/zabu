package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.impl.*
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.ui.nvg.Font

open class ButtonStyles(
    component: Button,
    theme: AbstractTheme,
) : ComponentStyles<Button, ButtonStyles>(
    component,
    theme
) {

    open var backgroundColor = Color(250, 251, 252)
    open var foregroundColor = theme.colorScheme.foreground
    open var hoveredForegroundColor = theme.colorScheme.foregroundHover
    open var hoveredBackgroundColor = theme.colorScheme.backgroundHover

    open var boxShadow = Shadow(
        offsetY = 2f,
        radius = CornerRadius(
            value = 4
        ),
        blur = 0f,
        innerColor = Color(27, 31, 35, 0.04f),
        outerColor = Color(27, 31, 35, 0f)
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

    open var padding: Padding = Padding(
        top = 6,
        right = 16,
        bottom = 6,
        left = 16
    )

    open var border = Border(
        thickness = 2f,
        color = theme.colorScheme.border
    )

    open var cornerRadius = CornerRadius(
        value = 4
    )

    open var fontStyles = FontStyles(
        family = "Inter",
        size = 16f,
        weight = Font.FontWeight.MEDIUM,
        lineHeight = 1f,
        align = Font.FontAlign.CENTER
    )
}
