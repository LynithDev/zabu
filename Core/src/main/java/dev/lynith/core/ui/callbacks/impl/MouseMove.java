package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MouseMove extends ComponentCallbacks.CallbackInterface {
    void handle(int x, int y);

    default boolean allowed(Component<?, ?> component, int x, int y) {
        return Component.intersecting(component, x, y);
    }
}
