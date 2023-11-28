package dev.lynith.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.MinecraftInit;
import dev.lynith.core.events.impl.MouseClick;
import dev.lynith.core.events.impl.ShutdownEvent;
import dev.lynith.core.plugins.PluginManager;
import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.theme.ThemeManager;
import dev.lynith.core.ui.theme.dark.ThemeDark;
import lombok.Getter;

import java.lang.instrument.Instrumentation;

public class ClientStartup {

    private final static Logger logger = new Logger("main");

    @Getter
    private static ClientStartup instance;

    public static void launch(IVersion version, Instrumentation inst) {
        if (instance != null) {
            logger.log("ClientStartup instance already exists. This shouldn't happen.");
            return;
        }

        logger.log("Launching version " + version.getMinecraft().getGameVersion());
        instance = new ClientStartup();
        instance.launchClient(version, inst);
    }

    @Getter private PluginManager pluginManager;
    @Getter private IVersion version;
    @Getter private EventBus eventBus;
    @Getter private ThemeManager themeManager;

    public void launchClient(IVersion version, Instrumentation inst) {
        this.version = version;

        this.eventBus = new EventBus();
        logger.log("Initialized EventBus");

        this.pluginManager = new PluginManager(inst);
        logger.log("Initialized PluginManager");

        this.themeManager = new ThemeManager();
        logger.log("Initialized ThemeManager");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            getEventBus().emit(ShutdownEvent.class);
        }));

        getEventBus().on(ShutdownEvent.class, () -> {
            logger.log("Preparing for shutdown");
        });

        getEventBus().on(MinecraftInit.class, () -> {
            Button button = new Button();
            button.init();

            themeManager.setCurrentTheme(ThemeDark.class);
            button.init();
        });
    }

    public static void main(String[] args) {
        System.out.println("This shouldn't be run directly.");
        System.exit(1);
    }

}
