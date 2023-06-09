package dev.lynith.onenineteenfour;

import com.mojang.realmsclient.RealmsMainScreen;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.LanguageSelectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.renderer.GameRenderer;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class Game implements IGame {

    @Override
    public int getFps() {
        return Minecraft.getInstance().getFps();
    }

    @Override
    public File getGameDir() {
        return Minecraft.getInstance().gameDirectory;
    }

    @Override
    public Map<GuiScreens, Object> getGuiScreens() {
        return Map.of(
                GuiScreens.MAIN_MENU, TitleScreen.class,
                GuiScreens.INVENTORY, InventoryScreen.class,
                GuiScreens.OPTIONS_SCREEN, OptionsScreen.class,
                GuiScreens.MULTIPLAYER_SELECT, JoinMultiplayerScreen.class,
                GuiScreens.SINGLEPLAYER_SELECT, SelectWorldScreen.class,
                GuiScreens.LANGUAGE_SELECT, LanguageSelectScreen.class,
                GuiScreens.PAUSE_MENU, PauseScreen.class,
                GuiScreens.REALMS_SCREEN, RealmsMainScreen.class,
                GuiScreens.IN_GAME, Gui.class
        );
    }
}
