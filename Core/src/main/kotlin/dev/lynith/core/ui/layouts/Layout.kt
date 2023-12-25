package dev.lynith.core.ui.layouts

import dev.lynith.core.ui.components.Component
import dev.lynith.core.ui.components.ComponentWithChildren

abstract class Layout(
    val properties: LayoutProperties = LayoutProperties()
) {

    abstract fun position(component: ComponentWithChildren<*, *>)

}