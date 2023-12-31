package dev.lynith.core.ui.theme.base

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.impl.Button
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.theme.AbstractTheme

class ThemeBase : AbstractTheme(
    name = "Default",
    type = ThemeType.LIGHT,
) {

    override val colorScheme = DefaultColorScheme()

    // Custom styles for components
    override var componentMap: Map<Class<out Component<*, *>>, ComponentStyles<*, *>> = mapOf(
        Button::class.java to ButtonStyles(this),
    )


}
