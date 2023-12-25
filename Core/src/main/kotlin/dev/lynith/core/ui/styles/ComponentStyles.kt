package dev.lynith.core.ui.styles

import dev.lynith.core.ClientStartup
import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.impl.Position
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.ui.theme.ThemeManager

abstract class ComponentStyles<C : Component<C, CS>, CS : ComponentStyles<C, CS>>(
    private val component: C,
    private val theme: AbstractTheme
) {
    open var position = Position()

    // recursive generics are brain damaging
    class EmptyStyles<C : Component<C, EmptyStyles<C>>>(
        component: C
    ) : ComponentStyles<C, EmptyStyles<C>>(component, Platform.themeManager.currentTheme)
}
