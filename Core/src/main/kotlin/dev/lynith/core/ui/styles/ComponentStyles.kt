package dev.lynith.core.ui.styles

import dev.lynith.core.ui.components.Component

abstract class ComponentStyles<C : Component<C, CS>, CS : ComponentStyles<C, CS>>(
    private val component: C
) {
    val styles: MutableMap<String, Style<*>> = HashMap()

    fun <S : Style<*>> addStyle(style: S): CS {
        styles[style.propertyName] = style
        return this as CS
    }

    fun <S : Style<*>> getStyle(name: String): S {
        return styles[name] as S
    }

    fun getStyles(): List<Style<*>> {
        return ArrayList(styles.values)
    }

    // recursive generics are brain damaging
    class EmptyStyles<C : Component<C, EmptyStyles<C>>>(
        component: C
    ) : ComponentStyles<C, EmptyStyles<C>>(component)
}
