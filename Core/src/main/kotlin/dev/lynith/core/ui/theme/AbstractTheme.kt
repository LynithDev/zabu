package dev.lynith.core.ui.theme

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.ComponentStyles

abstract class AbstractTheme constructor(
    var name: String,
    var type: ThemeType
) {

    abstract val colorScheme: AbstractColorScheme

    abstract var componentMap: Map<Class<out Component<*, *>>, ComponentStyles<*, *>>

    enum class ThemeType {
        DARK,
        LIGHT,
        HIGH_CONTRAST
    }

}
