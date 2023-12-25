package dev.lynith.core.ui.components

import dev.lynith.core.ui.callbacks.ComponentEvent
import dev.lynith.core.ui.callbacks.impl.Destroyed
import dev.lynith.core.ui.callbacks.impl.WindowResized
import dev.lynith.core.ui.layouts.DefaultLayout
import dev.lynith.core.ui.layouts.Layout
import dev.lynith.core.ui.layouts.LayoutProperties
import dev.lynith.core.ui.styles.ComponentWithChildrenStyles
import java.util.concurrent.CopyOnWriteArrayList

abstract class ComponentWithChildren<C : Component<C, S>, S : ComponentWithChildrenStyles<C, S>> : Component<C, S>() {

    val children: MutableList<Component<*, *>> = CopyOnWriteArrayList()
    var layout: Layout = DefaultLayout()

    fun reposition() {
        layout.position(this)
    }

    fun layout(func: LayoutProperties.() -> Unit): C {
        func(layout.properties)
        return this as C
    }

    override fun postRender(mouseX: Int, mouseY: Int, delta: Float) {
        for (child in children) {
            child.wrappedRender(mouseX, mouseY, delta)
        }

        super.postRender(mouseX, mouseY, delta)
    }

    override fun postInit() {
        super.postInit()

        on<Destroyed> {
            clearChildren()
        }

        for (child in children) {
            child.wrappedInit()
        }

        layout.position(this)
        on<WindowResized> {
            layout.position(this)
        }
    }

    // Children

    open fun child(child: Component<*, *>) {
        children.add(child)
    }

    fun children(vararg children: Component<*, *>) {
        for (c in children) {
            child(c.apply {
                parent = this@ComponentWithChildren
            })
        }
    }

    fun children(children: List<Component<*, *>>) {
        for (c in children) {
            child(c.apply {
                parent = this@ComponentWithChildren
            })
        }
    }

    fun removeChild(child: Component<*, *>) {
        children.remove(child)
    }

    fun clearChildren() {
        children.clear()
    }

}