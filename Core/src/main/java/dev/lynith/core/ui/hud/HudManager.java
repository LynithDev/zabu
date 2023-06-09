package dev.lynith.core.ui.hud;

import dev.lynith.core.Logger;
import dev.lynith.core.config.ConfigManager;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.hud.impl.FPSComponent;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.KeyboardHelper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    private static HudManager instance;
    public static HudManager getInstance() {
        if (instance == null) instance = new HudManager();
        return instance;
    }

    private final Logger logger = new Logger("HudManager");

    @Getter
    private InGameHud inGameHud;

    @Getter
    private List<Component> components = new ArrayList<>();

    public HudManager() {}

    public void init() {
        addComponent(new FPSComponent());

        inGameHud = new InGameHud();
        logger.log("Loaded hud manager");
    }

    public void addComponent(Component component) {
        components.add(component);
        ConfigManager.getInstance().register(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Subscribe
    private void onKeyPressed(KeyPressEvent event) {
        if (event.getKey().equals(KeyboardHelper.KEY_RSHIFT)) {
            event.getBridge().getRenderer().setCurrentScreen(GuiScreens.HUD_CONFIG_SCREEN, new HudConfig());
        }
    }

}
