package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.styles.impl.FontStyles
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.utils.nvg.Font

open class LabelStyles(
    component: Label,
    theme: AbstractTheme
) : ComponentStyles<Label, LabelStyles>(
    component,
    theme
) {

    open var fontStyles = FontStyles()

    open var color = theme.colorScheme.foreground

}