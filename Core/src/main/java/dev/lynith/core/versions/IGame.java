package dev.lynith.core.versions;

import dev.lynith.core.utils.GuiScreens;

import java.util.Map;

public interface IGame {

    int getFps();

    Map<GuiScreens, Object> getGuiScreens();

    default void shutdown(int exitCode) {
        System.exit(exitCode);
    }

    default void shutdown() {
        shutdown(0);
    }

}
