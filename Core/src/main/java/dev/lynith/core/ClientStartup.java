package dev.lynith.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.bridge.IVersionMain;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.EventCallback;
import dev.lynith.core.events.impl.MinecraftInit;
import dev.lynith.core.events.impl.MouseClick;
import dev.lynith.core.events.impl.ShutdownEvent;
import dev.lynith.core.plugins.PluginManager;
import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.theme.ThemeManager;
import dev.lynith.core.ui.theme.dark.ThemeDark;
import dev.lynith.core.utils.NanoVGManager;
import lombok.AccessLevel;
import lombok.Getter;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;

@Getter
public class ClientStartup {

    @Getter(AccessLevel.NONE)
    private final static Logger logger = new Logger("main");

    private static ClientStartup instance;

    public static ClientStartup getInstance() {
        if (instance == null) {
            ClientStartup.launch();
        }

        return instance;
    }

    public static void launch() {
        Class<?> versionMain;

        try {
            versionMain = Class.forName("dev.lynith.start.VersionMain");
        } catch (Exception e) {
            logger.error("Invalid build");
            logger.error("dev.lynith.start.VersionMain was not found!");
            return;
        }

        try {
            IVersionMain versionMainInstance = (IVersionMain) versionMain.getConstructor().newInstance();
            Class<? extends IVersion> versionClass = versionMainInstance.getVersion();

            IVersion version = versionClass.getConstructor().newInstance();
            logger.log("Found bridge");

            ClientStartup.launch(version);
        } catch (Exception e) {
            logger.error("Failed to launch client");
            e.printStackTrace();
        }

    }

    public static void launch(IVersion version) {
        if (instance != null) {
            logger.log("ClientStartup instance already exists. This shouldn't happen.");
            return;
        }

        logger.log("Launching version " + version.getMinecraft().getGameVersion());
        instance = new ClientStartup();
        instance.launchClient(version);
    }

    private PluginManager pluginManager;
    private IVersion version;
    private EventBus<EventCallback> eventBus;
    private ThemeManager themeManager;
    private long nvgContext;

    public void launchClient(IVersion version) {
        this.version = version;

        this.eventBus = new EventBus<>();
        logger.log("Initialized EventBus");

//        this.pluginManager = new PluginManager();
//        logger.log("Initialized PluginManager");

        this.themeManager = new ThemeManager();
        logger.log("Initialized ThemeManager");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            getEventBus().emit(ShutdownEvent.class);
        }));

        getEventBus().on(ShutdownEvent.class, () -> {
            logger.log("Preparing for shutdown");
        });

        getEventBus().once(MinecraftInit.class, () -> {
            nvgContext = NanoVGManager.createContext();
            logger.log("Initialized NanoVG");
        });
    }

    public static void main(String[] args) {
        System.out.println("This shouldn't be run directly.");
        System.exit(1);
    }

}
