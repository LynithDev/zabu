package dev.lynith.core;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.GuiScreenChangedEvent;
import dev.lynith.core.modules.ModuleManager;
import dev.lynith.core.ui.hud.HUDManager;
import dev.lynith.core.ui.impl.ScreenWrapper;
import dev.lynith.core.ui.impl.screens.ZabuMainMenu;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IVersion;
import lombok.Getter;

import java.lang.instrument.Instrumentation;

public class ClientStartup {

    private final Logger logger;

    @Getter
    private final IVersion bridge;

    @Getter
    private static ClientStartup instance;

    public static void start(IVersion version, Instrumentation inst) {
        instance = new ClientStartup(version, inst);
    }

    public ClientStartup(IVersion version, Instrumentation inst) {
        if (instance == null)
            instance = this;
        this.logger = new Logger("main");
        logger.log("Client Startup");

        this.bridge = version;
        logger.log("Loaded bridge for Minecraft " + bridge.getVersion());

        EventBus.getEventBus().register(this);
        logger.log("Registered event bus");

        ModuleManager.getInstance();
        logger.log("Loaded module manager");

        HUDManager.getInstance();
        logger.log("Loaded HUD Manager");
    }

    @Subscribe
    public void onGuiScreen(GuiScreenChangedEvent event) {
        if (event.getScreenType() == GuiScreens.MAIN_MENU) {
            event.getBridge().getRenderer().setCurrentScreen(new ScreenWrapper(GuiScreens.MAIN_MENU, new ZabuMainMenu()));
        }
    }

}
