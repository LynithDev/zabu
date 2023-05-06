package dev.lynith.Core;

import dev.lynith.Core.events.EventBus;
import dev.lynith.Core.events.Subscribe;
import dev.lynith.Core.events.impl.GuiScreenChangedEvent;
import dev.lynith.Core.ui.impl.ScreenWrapper;
import dev.lynith.Core.ui.impl.ZabuMainMenu;
import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.versions.IVersion;
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
        logger.log("Loaded version " + bridge.getVersion());

        EventBus.getEventBus().register(this);
        logger.log("Registered self");
    }

    @Subscribe
    public void onGuiScreen(GuiScreenChangedEvent event) {
        if (event.getScreenType() == GuiScreens.MAIN_MENU) {
            event.getBridge().getRenderer().setCurrentScreen(new ScreenWrapper(GuiScreens.MAIN_MENU, new ZabuMainMenu()));
        }
    }

}
