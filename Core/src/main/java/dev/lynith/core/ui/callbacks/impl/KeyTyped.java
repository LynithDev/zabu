package dev.lynith.core.ui.callbacks.impl;

import dev.lynith.core.ui.callbacks.ComponentCallbacks;

public interface KeyTyped extends ComponentCallbacks.CallbackInterface {
    void handle(int keyCode);
}
