package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.theme.AbstractTheme

class HoveredButtonStyles @JvmOverloads constructor(
    theme: AbstractTheme,
    state: ComponentStyleState = ComponentStyleState.HOVER
) : ButtonStyles(
    theme,
    state
) {

    override var backgroundColor = Color(255, 0, 0)

    override var hoverStyles: Class<out ButtonStyles>? = null

}