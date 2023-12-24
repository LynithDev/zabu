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

    open var backgroundColor = theme.colorScheme.background
    open var foregroundColor = theme.colorScheme.foreground

    open var border = Border(
        thickness = 2,
        color = theme.colorScheme.backgroundTertiary
    )

    open var cornerRadius = CornerRadius(
        value = 7
    )

    open var fontStyles = FontStyles(
        family = "Inter",
        size = 16f,
        weight = Font.FontWeight.MEDIUM,
        lineHeight = 1f,
        align = Font.FontAlign.CENTER
    )
}
