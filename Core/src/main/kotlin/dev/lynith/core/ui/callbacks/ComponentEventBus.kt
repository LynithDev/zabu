package dev.lynith.core.ui.callbacks

import dev.lynith.core.Logger
import dev.lynith.core.events.Event
import dev.lynith.core.events.EventBus
import dev.lynith.core.ui.components.Component
import java.util.concurrent.CopyOnWriteArrayList

class ComponentEventBus {

    companion object {
        val instance = ComponentEventBus()
    }

    private val events: MutableMap<Class<out ComponentEvent>, MutableList<StoredEvent<in ComponentEvent>>> = mutableMapOf()

    fun <T : ComponentEvent> on(component: Component<*,*>, eventClazz: Class<T>, callback: (T) -> Unit, once: Boolean = false) {
        val storedEvent = StoredEvent(component, callback, once) as StoredEvent<in ComponentEvent>
        events[eventClazz] = (events[eventClazz] ?: CopyOnWriteArrayList()).apply {
            add(storedEvent)
        }
    }

    fun emit(event: ComponentEvent) {
        val callbacks = events[event.javaClass] ?: return

        for (callback in callbacks) {
            callback.call(event)
            if (callback.once) {
                events[event.javaClass]?.remove(callback)
            }
        }
    }

    private class StoredEvent<E : ComponentEvent> (
        val component: Component<*, *>,
        val callback: (E) -> Unit,
        var once: Boolean = false
    ) {
        fun call(event: E) {
            if (event.shouldPass(component)) {
                callback.invoke(event)
                event.postPass(component)
            }

        }

        override fun toString(): String {
            return "StoredEvent(callback=${callback.javaClass.simpleName}, once=$once)"
        }
    }

}