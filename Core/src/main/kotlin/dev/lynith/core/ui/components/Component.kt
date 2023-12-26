package dev.lynith.core.ui.components

import dev.lynith.core.events.EventBus
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.callbacks.ComponentEvent
import dev.lynith.core.ui.callbacks.ComponentEventBus
import dev.lynith.core.ui.callbacks.impl.Clicked
import dev.lynith.core.ui.callbacks.impl.CursorMoved
import dev.lynith.core.ui.callbacks.impl.Pressed
import dev.lynith.core.ui.callbacks.impl.Released
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.nvg.NanoVGHelper
import dev.lynith.core.ui.styles.impl.Border
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.units.px

abstract class Component<C : Component<C, S>, S : ComponentStyles<C, S>> : NanoVGHelper() {
    open var parent: ComponentWithChildren<*, *>? = null
    open var screen: Screen? = null
    open var mouseOver: Boolean = false

    open var bounds: BoundingBox = BoundingBox()

    /**
     * Custom properties for a component. These are not used by the engine at all, and are only really useful for
     * storing data that you want to persist with the component such as a boolean state whether a component was pressed
     * or not.
     */
    val customProperties = mutableMapOf<String, Any>()

    /**
     * The event bus for this component. An event bus for a specific component should reduce slight overhead
     */
    private val eventBus = ComponentEventBus.instance

    /**
     * The styles for this component. This is a mutable property so that you can change the styles of a component
     */
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
    protected open fun preInit() {
        on<Pressed> {
            customProperties["pressed"] = true
        }

        on<Released> {
            if (customProperties.contains("pressed") && customProperties["pressed"] == true) {
                customProperties.remove("pressed")
                emit(Clicked(it.mouseX, it.mouseY, it.button))
            }
        }

        on<CursorMoved> {
            mouseOver = bounds.contains(it.mouseX, it.mouseY)
        }
    }
    abstract fun init()
    protected open fun postInit() {}

    open fun wrappedInit() {
        preInit()
        init()
        postInit()
    }
    // ---- INIT ----

    // ---- EVENTS -----
    open fun <T : ComponentEvent> on(once: Boolean, clazz: Class<T>, callback: (T) -> Unit) {
        eventBus.on(this, clazz, callback, once)
    }

    inline fun <reified T : ComponentEvent> on(noinline callback: (T) -> Unit) {
        on(false, T::class.java, callback)
    }

    inline fun <reified T : ComponentEvent> once(noinline callback: (T) -> Unit) {
        on(true, T::class.java, callback)
    }

    fun emit(event: ComponentEvent) {
        eventBus.emit(event)
    }
    // ---- EVENTS -----

    // Utils
    fun debugBox() {
        val color = Color(
            (hashCode() shr 16) and 0xFF,
            (hashCode() shr 8) and 0xFF,
            hashCode() and 0xFF
        )

        rectangle(bounds, Color.TRANSPARENT, Border(
            thickness = 2.px,
            color = color
        ))
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
