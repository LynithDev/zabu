package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MouseLeave extends ComponentCallbacks.CallbackInterface {
    void handle(int mouseX, int mouseY);
}
