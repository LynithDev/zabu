package dev.lynith.core.modules.impl;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.ChatSendEvent;
import dev.lynith.core.events.impl.WebsocketMessageEvent;
import dev.lynith.core.modules.Module;
import dev.lynith.core.input.Keyboard;
import dev.lynith.core.websocket.ZabuWS;

public class WebsocketChatModule extends Module {
    public WebsocketChatModule() {
        super("Websocket Chat", "Websocket chat", Keyboard.KEY_C);
        EventBus.getEventBus().register(this);
    }

    @Override
    public void onEnable() {
        if (ZabuWS.getInstance().isClosed()) {
            logger.error("Websocket is closed");
            setEnabled(false);
        }
    }

    @Subscribe
    private void onChatMessage(ChatSendEvent event) {
        if (event.getMessage().startsWith(".ws ")) {
            ZabuWS.getInstance().send(event.getMessage());
        }
    }

    @Subscribe
    private void onWebsocketMessage(WebsocketMessageEvent event) {
        logger.log("Received message: " + event.getMessage());
    }

    @Override
    public void onDisable() {

    }
}
