package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface MousePress extends ComponentCallbacks.CallbackInterface {
    void handle(int x, int y);
}
