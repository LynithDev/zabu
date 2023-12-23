package dev.lynith.core.ui.components

import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.events.EventBus
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.nvg.NanoVGHelper

abstract class Component<C : Component<C, S>, S : ComponentStyles<C, S>> : NanoVGHelper() {
    open var parent: Component<*, *>? = null
    open var bounds: BoundingBox = BoundingBox()

    /**
     * Custom properties for a component. These are not used by the engine at all, and are only really useful for
     * storing data that you want to persist with the component such as a boolean state whether a component was pressed
     * or not.
     */
    val customProperties = mutableMapOf<String, Any>()

    private val eventBus = EventBus<ComponentEvent>()
    abstract var styles: S

    // ---- RENDER ----
    protected open fun preRender(mouseX: Int, mouseY: Int, delta: Float) {
        createFrame()
    }

    abstract fun render(mouseX: Int, mouseY: Int, delta: Float)
    protected open fun postRender(mouseX: Int, mouseY: Int, delta: Float) {
        endFrame()
    }

    fun wrappedRender(mouseX: Int, mouseY: Int, delta: Float) {
        preRender(mouseX, mouseY, delta)
        render(mouseX, mouseY, delta)
        postRender(mouseX, mouseY, delta)
    }
    // ---- RENDER ----

    // ---- INIT ----
    protected open fun preInit() {}
    abstract fun init()
    protected open fun postInit() {}
    open fun wrappedInit() {
        preInit()
        init()
        postInit()
    }
    // ---- INIT ----

    open fun <T : ComponentEvent> on(once: Boolean, clazz: Class<T>, callback: (T) -> Unit) {
        eventBus.on(clazz, callback, once)
    }

    inline fun <reified T : ComponentEvent> on(noinline callback: (T) -> Unit) {
        on(false, T::class.java, callback)
    }

    inline fun <reified T : ComponentEvent> once(noinline callback: (T) -> Unit) {
        on(true, T::class.java, callback)
    }

    open fun emit(event: ComponentEvent) {
        if (event.shouldPass(this)) {
            eventBus.emit(event)
            event.postPass(this)
        }
    }

    // Blocks
    fun style(block: S.() -> Unit): C {
        styles.apply(block)
        return this as C
    }

    fun configure(block: C.() -> Unit): C {
        block(this as C)
        return this as C
    }

}
