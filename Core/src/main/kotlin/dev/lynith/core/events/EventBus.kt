package dev.lynith.core.events

import dev.lynith.core.Logger
import java.util.concurrent.CopyOnWriteArrayList

class EventBus<E : Event> @JvmOverloads constructor(
    name: String = "eventbus"
) {

    private val logger: Logger = Logger(name)
    private val events: CopyOnWriteArrayList<StoredEvent<in E>> = CopyOnWriteArrayList()

    fun <T : E> on(eventClazz: Class<T>, callback: (T) -> Unit, once: Boolean = false) {
        val storedEvent = StoredEvent(eventClazz, callback, once)
        events.add(storedEvent as StoredEvent<in E>)
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

    fun emit(event: E) {
        events.forEach { storedEvent ->
            if (storedEvent.eventClazz.isAssignableFrom(event.javaClass)) {
                storedEvent.call(event)

                if (storedEvent.once) {
                    events.remove(storedEvent)
                }
            }
        }
    }

    private class StoredEvent<E : Event> (
        val eventClazz: Class<E>,
        val callback: (E) -> Unit,
        var once: Boolean = false
    ) {
        fun call(event: E) {
            callback.invoke(event)
        }
    }

}
