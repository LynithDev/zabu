package dev.lynith.core.ui.components

import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.ui.components.features.Children
import dev.lynith.core.ui.styles.ComponentStyles

abstract class Screen : Component<Screen, ComponentStyles.EmptyStyles<Screen>>(), Children {
    override var children: MutableList<Component<*, *>> = ArrayList()

    override fun postRender(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
        for (child in children) {
            child.render(ctx, mouseX, mouseY, delta)
        }

        super.postRender(ctx, mouseX, mouseY, delta)
    }

    override fun postInit() {
        super.postInit()

        for (child in children) {
            child.init()
        }
    }

    override fun <T : ComponentCallback> on(event: Class<T>, callback: T, once: Boolean) {
        super.on(event, callback, once)

        for (child in children) {
            child.on(event, callback, once)
        }
    }

    override var styles = ComponentStyles.EmptyStyles(this)
}
