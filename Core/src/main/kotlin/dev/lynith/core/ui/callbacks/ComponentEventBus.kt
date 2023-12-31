package dev.lynith.core.ui.callbacks

import dev.lynith.core.Logger
import dev.lynith.core.events.Event
import dev.lynith.core.events.EventBus
import dev.lynith.core.ui.callbacks.impl.CursorMoved
import dev.lynith.core.ui.components.Component
import java.util.concurrent.CopyOnWriteArrayList

class ComponentEventBus {

    private val events: MutableMap<Class<out ComponentEvent>, MutableList<StoredEvent<in ComponentEvent>>> = mutableMapOf()

    fun <T : ComponentEvent> on(component: Component<*,*>, eventClazz: Class<T>, callback: (T) -> Unit, once: Boolean = false) {
        val storedEvent = StoredEvent(component, callback, once) as StoredEvent<in ComponentEvent>
        events[eventClazz] = (events[eventClazz] ?: CopyOnWriteArrayList()).apply {
            add(storedEvent)
        }
    }

    fun unregister(component: Component<*, *>) {
        for (event in events.values) {
            event.removeIf { it.component == component }
        }
    }

    fun emit(event: ComponentEvent) {
        val callbacks = events[event.javaClass] ?: return

        for (callback in callbacks) {
            if (event.shouldPass(callback.component)) {
                callback.callback.invoke(event)
                event.postPass(callback.component)

                if (callback.once) {
                    events[event.javaClass]?.remove(callback)
                }
            }
        }
    }

    private class StoredEvent<E : ComponentEvent> (
        val component: Component<*, *>,
        val callback: (E) -> Unit,
        var once: Boolean = false
    ) {
        override fun toString(): String {
            return "StoredEvent(" +
                "component=${component.javaClass.simpleName}@${component.hashCode()}, " +
                "callback=${callback.javaClass.simpleName}@${callback.hashCode()}, " +
                "once=$once" +
                ")"
        }
    }

}