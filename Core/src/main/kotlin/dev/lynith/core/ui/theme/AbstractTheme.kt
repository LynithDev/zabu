package dev.lynith.core.ui.theme

import dev.lynith.core.ClientStartup
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.theme.base.ButtonStyles
import dev.lynith.core.ui.theme.base.LabelStyles

abstract class AbstractTheme constructor(
    var name: String,
    var type: ThemeType
) {

    abstract val colorScheme: AbstractColorScheme

    abstract fun getButtonStyles(button: Button): ButtonStyles
    abstract fun getLabelStyles(label: Label): LabelStyles

    enum class ThemeType {
        DARK,
        LIGHT,
        HIGH_CONTRAST
    }

}
