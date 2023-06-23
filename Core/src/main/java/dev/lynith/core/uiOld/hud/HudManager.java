package dev.lynith.core.uiOld.hud;

import dev.lynith.core.Logger;
import dev.lynith.core.config.ConfigManager;
import dev.lynith.core.uiOld.Component;
import dev.lynith.core.uiOld.hud.impl.FPSComponent;
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
    private final List<Component> components = new ArrayList<>();

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

}
