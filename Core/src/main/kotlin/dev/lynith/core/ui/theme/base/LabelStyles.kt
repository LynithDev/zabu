package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.impl.FontStyles
import dev.lynith.core.ui.theme.AbstractTheme

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