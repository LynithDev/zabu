package dev.lynith.core.events.impl;

import dev.lynith.core.events.Event;
import dev.lynith.core.utils.Key;
import lombok.Getter;

public class KeyPressEvent extends Event {

    @Getter
    private final Key key;

    public KeyPressEvent(Key key) {
        this.key = key;
    }

}
