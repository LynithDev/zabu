package dev.lynith.core.ui.theme.dark

import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.ui.theme.base.ButtonStyles
import dev.lynith.core.ui.theme.base.DefaultColorScheme
import dev.lynith.core.ui.theme.base.LabelStyles

class ThemeDark : AbstractTheme(
    name = "Default Dark",
    type = ThemeType.DARK
) {

    override val colorScheme = DefaultColorScheme()

    override fun getButtonStyles(button: Button) = ButtonStyles(button, this)
    override fun getLabelStyles(label: Label) = LabelStyles(label, this)
}
