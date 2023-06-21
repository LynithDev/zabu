package dev.lynith.core.websocket;

import dev.lynith.core.Logger;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.WebsocketMessageEvent;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ZabuWS extends WebSocketClient {

    private final Logger logger = new Logger("ws");

    public ZabuWS() {
        super(URI.create("ws://127.0.0.1:8080/"));
    }

    private static ZabuWS instance;
    public static ZabuWS getInstance() {
        if (instance == null) instance = new ZabuWS();
        return instance;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.log("Connected to websocket server");
    }

    @Override
    public void onMessage(String message) {
        EventBus.getEventBus().post(new WebsocketMessageEvent(message));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.log("Disconnected from websocket server");
    }

    @Override
    public void onError(Exception ex) {
        logger.log("Error: " + ex.getMessage());
    }
}
