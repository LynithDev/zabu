package dev.lynith.core.events.impl;

import dev.lynith.core.events.EventCallback;

public interface MinecraftGuiChanged extends EventCallback {

    void invoke(String screenClass);

}
