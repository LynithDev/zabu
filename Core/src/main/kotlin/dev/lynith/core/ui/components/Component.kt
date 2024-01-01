package dev.lynith.core.ui.components

import dev.lynith.core.Platform
import dev.lynith.core.ui.BoundingBox
import dev.lynith.core.ui.callbacks.ComponentEvent
import dev.lynith.core.ui.callbacks.impl.*
import dev.lynith.core.ui.components.impl.CustomWidget
import dev.lynith.core.ui.components.impl.Label
import dev.lynith.core.ui.styles.ComponentStyles
import dev.lynith.core.ui.nvg.NanoVGHelper
import dev.lynith.core.ui.styles.impl.Border
import dev.lynith.core.ui.styles.impl.Color
import dev.lynith.core.ui.theme.AbstractTheme
import dev.lynith.core.ui.units.px

abstract class Component<C : Component<C, S>, S : ComponentStyles<C, S>> : NanoVGHelper() {
    open var parent: ComponentWithChildren<*, *>? = null
    open var screen: Screen? = null
    open var cursorInside: Boolean = false

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
    private val eventBus = Platform.componentEventBus

    /**
     * The styles for this component. This is a mutable property so that you can change the styles of a component
     */
    private var baseStyles: S? = null
    private var hoverStyles: S? = null
    private val activeStateStyles: S
        get() {
            return if (hoverStyles != null && cursorInside) {
                hoverStyles!!
            } else {
                baseStyles!!
            }
        }

    open val styles: S
        get() {
            return activeStateStyles
        }

    // ---- RENDER ----
    protected open fun preRender(mouseX: Int, mouseY: Int, delta: Float) {
        if (bounds.contains(mouseX, mouseY)) {
            if (!cursorInside) {
                cursorInside = true
                emit(CursorEnter(mouseX, mouseY))
            }
        } else {
            if (cursorInside) {
                cursorInside = false
                emit(CursorExit(mouseX, mouseY))
            }
        }

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
    private fun initBaseStyles() {
        if (baseStyles == null) {
            val componentMapStyles = Platform.themeManager.currentTheme.componentMap[this::class.java]
            baseStyles = if (componentMapStyles != null) {
                componentMapStyles.javaClass.getConstructor(AbstractTheme::class.java).newInstance(Platform.themeManager.currentTheme)
            } else {
                ComponentStyles.BaseStyles<CustomWidget>()
            } as S
        }
    }

    private fun initHoverStyles() {
        if (hoverStyles == null && styles.hoverStyles != null) {
            hoverStyles = styles.hoverStyles!!.getConstructor(AbstractTheme::class.java).newInstance(Platform.themeManager.currentTheme) as S
        }
    }

    protected open fun preInit() {
        initBaseStyles()
        initHoverStyles()

        on<Pressed> {
            customProperties["pressed"] = true
        }

        on<Released> {
            if (customProperties.contains("pressed") && customProperties["pressed"] == true) {
                customProperties.remove("pressed")
                emit(Clicked(it.mouseX, it.mouseY, it.button))
            }
        }

        once<Destroyed> {
            eventBus.unregister(this)
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

    open fun unregister() {
        eventBus.unregister(this)
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
        initBaseStyles()
        baseStyles!!.block()
        return this as C
    }

    fun hoverStyles(block: S.() -> Unit): C {
        initHoverStyles()
        hoverStyles!!.block()
        return this as C
    }

    fun configure(block: C.() -> Unit): C {
        block(this as C)
        return this as C
    }

}
