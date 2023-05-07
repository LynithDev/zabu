package dev.lynith.onenineteenfour;

import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.Tuple;
import dev.lynith.Core.versions.IGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.multiplayer.ServerList;

import java.util.List;
import java.util.Map;

public class Game implements IGame {

    @Override
    public int getFps() {
        return Minecraft.getInstance().getFps();
    }

    @Override
    public Map<GuiScreens, Object> getGuiScreens() {
        return Map.of(
                GuiScreens.MAIN_MENU, TitleScreen.class,
                GuiScreens.INVENTORY, InventoryScreen.class,
                GuiScreens.OPTIONS_SCREEN, OptionsScreen.class,
                GuiScreens.MULTIPLAYER_SELECT, JoinMultiplayerScreen.class,
                GuiScreens.SINGLEPLAYER_SELECT, SelectWorldScreen.class
        );
    }
}
