package dev.lynith.core.ui.components.features

import dev.lynith.core.ui.components.Component

interface Children {

    val children: MutableList<Component<*, *>>

    fun child(child: Component<*, *>) {
        children.add(child)
    }

    fun children(vararg children: Component<*, *>) {
        for (c in children) {
            child(c.apply {
                parent = this
            })
        }
    }

    fun children(children: List<Component<*, *>>) {
        this.children.addAll(children)
    }

    fun removeChild(child: Component<*, *>) {
        children.remove(child)
    }

    fun clearChildren() {
        children.clear()
    }
}
