package dev.lynith.core.ui.theme.dark

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.ui.theme.base.ButtonStyles

class ThemeDark : AbstractTheme(
    name = "Default Dark",
    type = ThemeType.DARK
) {

    override fun getButtonStyles(button: Button) = DarkButtonStyles(button)

    override fun getLabelStyles(label: Label) = DarkLabelStyles(label)
}
