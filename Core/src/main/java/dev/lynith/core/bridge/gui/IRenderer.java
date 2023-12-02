package dev.lynith.core.bridge.gui;

import dev.lynith.core.ui.components.Screen;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public interface IRenderer {

//    void rect(int x, int y, int width, int height, int color);

    GuiType getCurrentScreen();
    HashMap<Class<?>, GuiType> getScreenMap();

    boolean displayScreen(GuiType screen, Object... args);
    boolean displayScreen(Screen screen, Object... args);

    @Getter
    enum GuiType {
        MAIN_MENU,
        PAUSE_MENU,
        MULTIPLAYER_SELECTOR,
        SINGLEPLAYER_SELECTOR,
        INGAME,
        CUSTOM,
        UNKNOWN;

        @Setter
        private Class<?> clazz;
    }

}