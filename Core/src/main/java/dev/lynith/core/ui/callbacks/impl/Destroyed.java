package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface Destroyed extends ComponentCallbacks.CallbackInterface {
    void handle();

    default boolean allowed(Component<?, ?> component) {
        return true;
    }
}
