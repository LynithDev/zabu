package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MouseEnter extends ComponentCallbacks.CallbackInterface {
    void handle(int mouseX, int mouseY);

    default boolean allowed(Component<?,?> component, int x, int y) {
        if (Component.intersecting(component, x, y)) {
            return true;
        } else {
            component.callCallbacks(MouseLeave.class, x, y);
            return false;
        }
    }
}
