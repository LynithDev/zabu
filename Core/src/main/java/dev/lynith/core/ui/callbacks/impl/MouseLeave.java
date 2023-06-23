package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MouseLeave extends ComponentCallbacks.CallbackInterface {
    void handle(int mouseX, int mouseY);

    default boolean allowed(Component<?,?> component, int x, int y) {
        return !Component.intersecting(component, x, y);
    }
}
