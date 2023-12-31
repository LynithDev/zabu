package dev.lynith.core.ui.theme.dark

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.ui.theme.base.ButtonStyles

class ThemeDark : AbstractTheme(
    name = "Default Dark",
    type = ThemeType.DARK
) {

    override val colorScheme = DarkColorScheme()

    // Custom styles for components
    override var componentMap: Map<Class<out Component<*, *>>, ComponentStyles<*, *>> = mapOf(
        Button::class.java to ButtonStyles(this),
    )

}
