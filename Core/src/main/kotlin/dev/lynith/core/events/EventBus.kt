package dev.lynith.core.events

import dev.lynith.core.Logger
import java.util.concurrent.CopyOnWriteArrayList

open class EventBus<E : Event> @JvmOverloads constructor(
    name: String = "eventbus"
) {

    private val logger: Logger = Logger(name)
    private val events: MutableMap<Class<out E>, MutableList<StoredEvent<in E>>> = mutableMapOf()

    fun <T : E> on(eventClazz: Class<T>, callback: (T) -> Unit, once: Boolean = false) {
        val storedEvent = StoredEvent(callback, once) as StoredEvent<in E>
        events[eventClazz] = (events[eventClazz] ?: CopyOnWriteArrayList()).apply {
            add(storedEvent)
        }
    }

    fun <T : E> once(eventClazz: Class<T>, callback: (T) -> Unit) {
        on(eventClazz, callback, true)
    }

    inline fun <reified T : E> on(noinline callback: (T) -> Unit) {
        on(T::class.java, callback)
    }

    inline fun <reified T : E> once(noinline callback: (T) -> Unit) {
        on(T::class.java, callback, true)
    }

    open fun emit(event: E) {
        val callbacks = events[event.javaClass] ?: return

        for (callback in callbacks) {
            callback.call(event)
            if (callback.once) {
                events[event.javaClass]?.remove(callback)
            }
        }
    }

    private class StoredEvent<E : Event> (
        val callback: (E) -> Unit,
        var once: Boolean = false
    ) {
        fun call(event: E) {
            callback.invoke(event)
        }

        override fun toString(): String {
            return "StoredEvent(callback=${callback.javaClass.simpleName}, once=$once)"
        }
    }

}
