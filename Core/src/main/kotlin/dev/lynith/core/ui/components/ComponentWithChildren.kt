package dev.lynith.core.ui.components

import dev.lynith.core.Platform
import dev.lynith.core.ui.components.callbacks.Destroyed
import dev.lynith.core.ui.components.callbacks.WindowResized
import dev.lynith.core.ui.layouts.DefaultLayout
import dev.lynith.core.ui.layouts.Layout
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.styles.ComponentWithChildrenStyles
import dev.lynith.core.ui.styles.impl.Border
import dev.lynith.core.ui.styles.impl.Color
import java.util.concurrent.CopyOnWriteArrayList

abstract class ComponentWithChildren<C : Component<C, S>, S : ComponentWithChildrenStyles<C, S>> : Component<C, S>() {

    val children: MutableList<Component<*, *>> = CopyOnWriteArrayList()
    val layout: Layout = DefaultLayout()

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

    override fun emit(event: ComponentEvent) {
        super.emit(event)

        for (child in children) {
            child.emit(event)
        }
    }

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