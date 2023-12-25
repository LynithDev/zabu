package dev.lynith.core.ui.styles

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.impl.Padding
import dev.lynith.core.ui.theme.AbstractTheme

open class ComponentWithChildrenStyles<C : Component<C, CS>, CS : ComponentWithChildrenStyles<C, CS>>(
    component: C,
    theme: AbstractTheme
) : ComponentStyles<C, CS>(component, theme) {

    open var padding = Padding(
        top = 0,
        right = 0,
        bottom = 0,
        left = 0
    )

    class EmptyStyles<C : Component<C, EmptyStyles<C>>>(
        component: C
    ) : ComponentWithChildrenStyles<C, EmptyStyles<C>>(component, Platform.themeManager.currentTheme)

}