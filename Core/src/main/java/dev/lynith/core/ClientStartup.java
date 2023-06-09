package dev.lynith.core;

import dev.lynith.core.config.ConfigManager;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.Subscribe;
import dev.lynith.core.events.impl.GuiScreenChangedEvent;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.ShutdownEvent;
import dev.lynith.core.hud.HudManager;
import dev.lynith.core.input.KeyConfig;
import dev.lynith.core.modules.ModuleManager;
import dev.lynith.core.ui.ScreenWrapper;
import dev.lynith.core.ui.screen.MainMenu;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.websocket.ZabuWS;
import lombok.Getter;
import lombok.experimental.Accessors;

public class ClientStartup {

    private final Logger logger;

    @Getter
    private final IVersion bridge;

    @Getter
    private static ClientStartup instance;

    public static void start(IVersion version) {
        instance = new ClientStartup(version);
    }

    public ClientStartup(IVersion version) {
        if (instance == null) instance = this;
        this.logger = new Logger("main");
        logger.log("Client Startup");

        this.bridge = version;
        logger.log("Loaded bridge for Minecraft " + bridge.getVersion());

        EventBus.getEventBus().register(this);
        logger.log("Registered event bus");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> EventBus.getEventBus().post(new ShutdownEvent())));

        EventBus.getEventBus().register(ModuleManager.getInstance());
        EventBus.getEventBus().register(HudManager.getInstance());
        EventBus.getEventBus().register(KeyConfig.getInstance());

        if (!ZabuWS.getInstance().isOpen() || ZabuWS.getInstance().isClosed()) {
            ZabuWS.getInstance().connect();
        }
    }


    @Subscribe
    private void onMinecraftInit(MinecraftInitEvent event) {
        ConfigManager.getInstance().init();
        ModuleManager.getInstance().init();
        HudManager.getInstance().init();
    }

    @Subscribe
    private void onShutdown(ShutdownEvent event) {
        logger.log("Preparing for shutdown");
        ConfigManager.getInstance().save();
    }

    @Subscribe
    private void onGuiScreen(GuiScreenChangedEvent event) {
        if (event.getScreenType() == GuiScreens.MAIN_MENU) {
            event.getBridge().getRenderer().setCurrentScreen(new MainMenu());
        }
    }

}
