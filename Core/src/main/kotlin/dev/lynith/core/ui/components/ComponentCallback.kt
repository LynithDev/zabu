package dev.lynith.core.ui.components

interface ComponentCallback {

    fun invoke() {
        throw NotImplementedError()
    }

    /*
    Method called with reflection in EventBus
    void invoke(...args);
     */
}
