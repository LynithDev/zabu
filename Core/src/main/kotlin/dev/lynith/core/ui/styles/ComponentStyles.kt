package dev.lynith.core.ui.styles

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.styles.impl.*
import dev.lynith.core.ui.theme.AbstractTheme

abstract class ComponentStyles<C : Component<C, CS>, CS : ComponentStyles<C, CS>>(
    val theme: AbstractTheme,
    val state: ComponentStyleState = ComponentStyleState.NORMAL
) {

    open var position = Position()
    open var border = Border()
    open var backgroundColor = theme.colorScheme.background
    open var color = theme.colorScheme.foreground
    open var font = FontStyles()
    open var padding = Padding()
    open var boxShadow = Shadow()
    open var borderRadius = CornerRadius()

    // recursive generics are brain damaging
    class BaseStyles<C : Component<C, BaseStyles<C>>> : ComponentStyles<C, BaseStyles<C>>(Platform.themeManager.currentTheme, ComponentStyleState.NORMAL)

    open var hoverStyles: Class<out CS>? = null

    fun configure(func: CS.() -> Unit): CS {
        func.invoke(this as CS)
        return this as CS
    }

    enum class ComponentStyleState {
        NORMAL,
        HOVER,
        ACTIVE,
        DISABLED,
    }
}
