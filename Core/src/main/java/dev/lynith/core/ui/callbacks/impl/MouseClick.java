package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MouseClick extends ComponentCallbacks.CallbackInterface {
    void handle(int mouseX, int mouseY);
}
