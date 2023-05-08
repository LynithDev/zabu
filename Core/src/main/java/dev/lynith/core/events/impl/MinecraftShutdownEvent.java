package dev.lynith.core.events.impl;

import dev.lynith.core.events.Event;

public class MinecraftShutdownEvent extends Event {

    public MinecraftShutdownEvent() {
        super(false);
    }

}
