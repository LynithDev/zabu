package dev.lynith.Core.versions;

import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.Tuple;

import java.util.List;
import java.util.Map;

public interface IGame {

    int getFps();

    Map<GuiScreens, Object> getGuiScreens();

}
