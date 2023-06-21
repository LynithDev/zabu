package dev.lynith.core.input;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.ui.impl.screens.menu.ModMenu;

import java.util.HashMap;
import java.util.Map;

public class KeyConfig {

    public KeyConfig() {
        addCallback(Keyboard.KEY_RSHIFT, key -> {
            ClientStartup.getInstance().getBridge().getRenderer().setCurrentScreen(new ModMenu());
        });
    }

    private static KeyConfig instance;

    public static KeyConfig getInstance() {
        if (instance == null) {
            instance = new KeyConfig();
        }
        return instance;
    }

    @FunctionalInterface
    private interface KeyPressCallback {
        void handle(int key);
    }

    private final Map<Integer, KeyPressCallback> callbacks = new HashMap<>();

    public void addCallback(int key, KeyPressCallback callback) {
        callbacks.put(key, callback);
    }

    public void addCallback(Key key, KeyPressCallback callback) {
        addCallback(key.getCode(), callback);
    }

    public void removeCallback(int key) {
        callbacks.remove(key);
    }

    public void removeCallback(Key key) {
        removeCallback(key.getCode());
    }

    private void handle(int key) {
        KeyPressCallback callback = callbacks.get(key);
        if (callback != null) {
            callback.handle(key);
        }
    }

    @Subscribe
    private void onKeyPress(KeyPressEvent event) {
        handle(event.getKey().getCode());
    }

}
