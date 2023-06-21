package dev.lynith.core.events.impl;

import dev.lynith.core.events.Event;
import lombok.Getter;

public class ChatSendEvent extends Event {

    @Getter
    private final String message;

    public ChatSendEvent(String message) {
        super(false);
        this.message = message;
    }
}
