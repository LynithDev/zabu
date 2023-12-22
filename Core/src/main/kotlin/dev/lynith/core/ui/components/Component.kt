package dev.lynith.core.ui.components

import dev.lynith.core.bridge.gui.IRenderer
import dev.lynith.core.events.EventBus
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.NanoVGHelper

abstract class Component<C : Component<C, S>, S : ComponentStyles<C, S>> (
    open var parent: Component<*, *>? = null,
    open var bounds: BoundingBox = BoundingBox(),
) : NanoVGHelper() {
    private val eventBus = EventBus<ComponentCallback>()
    abstract var styles: S

    fun style(block: S.() -> Unit): C {
        styles.apply(block)
        return this as C
    }

    // ---- RENDER ----
    protected open fun preRender(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
        createFrame()
    }

    abstract fun render(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float)
    protected open fun postRender(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
        endFrame()
    }

    fun wrappedRender(ctx: IRenderer, mouseX: Int, mouseY: Int, delta: Float) {
        preRender(ctx, mouseX, mouseY, delta)
        render(ctx, mouseX, mouseY, delta)
        postRender(ctx, mouseX, mouseY, delta)
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

    /**
     * Adds a callback to the components event bus
     * @param event The event to listen for, must be a subclass of ComponentCallback
     * @param callback The callback to run when the event is emitted
     */
    open fun <T : ComponentCallback> on(event: Class<T>, callback: T) {
        on(event, callback, false)
    }

    /**
     * Adds a callback to the components event bus
     * @param event The event to listen for, must be a subclass of ComponentCallback
     * @param callback The callback to run when the event is emitted
     */
    open fun <T : ComponentCallback> on(event: Class<T>, callback: T, once: Boolean) {
        if (once) {
            eventBus.once(event, callback)
        } else {
            eventBus.on(event, callback)
        }
    }

    /**
     * Adds a callback to the components event bus that **will only be called once**
     * @param event The event to listen for, must be a subclass of ComponentCallback
     * @param callback The callback to run when the event is emitted
     */
    open fun <T : ComponentCallback> once(event: Class<T>, callback: T) {
        on(event, callback, true)
    }

    /**
     * Emit Call all types of a callback registered on the component's event bus
     * @param callbackClass The class of the callback to emit
     * @param args The arguments to pass to the callback, must match the callback's `invoke` method
     */
    open fun <T : ComponentCallback> emit(callbackClass: Class<T>, vararg args: Any?) {
        eventBus.emit(callbackClass, *args)
    }

    // Helper Utils
    var x: Float
        get() = bounds.x
        set(value) {
            bounds.x = value
        }

    var y: Float
        get() = bounds.y
        set(value) {
            bounds.y = value
        }

    var width: Float
        get() = bounds.width
        set(value) {
            bounds.width = value
        }

    var height: Float
        get() = bounds.height
        set(value) {
            bounds.height = value
        }

}
