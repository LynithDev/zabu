package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.theme.AbstractTheme

class ThemeBase : AbstractTheme(
    name = "Default",
    type = ThemeType.LIGHT,
) {

    override fun getButtonStyles(button: Button) = ButtonStyles(button)

    override fun getLabelStyles(label: Label) = LabelStyles(label)

}
