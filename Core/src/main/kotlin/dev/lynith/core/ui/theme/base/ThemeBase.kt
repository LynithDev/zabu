package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.theme.AbstractTheme

class ThemeBase : AbstractTheme(
    name = "Default",
    type = ThemeType.LIGHT,
) {

    override val colorScheme = DefaultColorScheme()

    override fun getButtonStyles(button: Button) = ButtonStyles(button, this)
    override fun getLabelStyles(label: Label) = LabelStyles(label, this)

}
