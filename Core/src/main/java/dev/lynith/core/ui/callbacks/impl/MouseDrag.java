package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MouseDrag extends ComponentCallbacks.CallbackInterface {
    void handle(int mouseX, int mouseY);

    default boolean allowed(Component<?,?> component, int mouseX, int mouseY) {
        if (component.style().draggable()) {
            if (component.properties().getBoolean("dragging") && component.properties().getBoolean("focused")) {
                return true;
            }

            if (Component.intersecting(component, mouseX, mouseY) && component.properties().getBoolean("focused")) {
                component.properties().put("dragging", true);
                return true;
            }
        }
        return false;
    }
}
