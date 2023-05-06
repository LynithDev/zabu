package dev.lynith.Core.versions;

import dev.lynith.Core.utils.GuiScreens;

import java.util.Map;

public interface IGame {

    int getFps();

    Map<GuiScreens, Object> getGuiScreens();

}
