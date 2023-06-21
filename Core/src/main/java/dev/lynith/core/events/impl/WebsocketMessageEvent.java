package dev.lynith.core.events.impl;

import dev.lynith.core.events.Event;
import lombok.Getter;

public class WebsocketMessageEvent extends Event {

    @Getter
    private final String message;

    public WebsocketMessageEvent(String message) {
        super(false);
        this.message = message;
    }

}
