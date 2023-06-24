package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface Resized extends ComponentCallbacks.CallbackInterface {
    void handle(int width, int height);

    default boolean allowed(Component<?, ?> component, int width, int height) {
        return true;
    }
}
