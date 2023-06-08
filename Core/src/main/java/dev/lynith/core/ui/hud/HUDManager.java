package dev.lynith.core.ui.hud;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.KeyboardHelper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HUDManager {

    private static HUDManager instance;
    public static HUDManager getInstance() {
        if (instance == null) instance = new HUDManager();
        return instance;
    }

    @Getter
    private List<Component> components = new ArrayList<>();

    public HUDManager() {
        EventBus.getEventBus().register(this);
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Subscribe
    public void onKeyPressed(KeyPressEvent event) {
        if (event.getKey().equals(KeyboardHelper.KEY_RSHIFT)) {
            event.getBridge().getRenderer().setCurrentScreen(new HUD());
        }
    }

}
