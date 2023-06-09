package dev.lynith.core.versions;

import dev.lynith.core.utils.GuiScreens;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public interface IGame {

    int getFps();

    File getGameDir();

    Map<GuiScreens, Object> getGuiScreens();

    default void shutdown(int exitCode) {
        System.exit(exitCode);
    }
    default void shutdown() {
        shutdown(0);
    }

}
