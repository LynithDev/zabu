package dev.lynith.onenineteenfour;

import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IGame;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;

import java.io.File;
import java.util.Map;

public class Game implements IGame {

    @Override
    public int getFps() {
        return MinecraftClient.getInstance().getCurrentFps();
    }

    @Override
    public File getGameDir() {
        return MinecraftClient.getInstance().runDirectory;
    }

    @Override
    public Map<GuiScreens, Object> getGuiScreens() {
        return Map.of(
                GuiScreens.MAIN_MENU, TitleScreen.class,
                GuiScreens.INVENTORY, InventoryScreen.class,
                GuiScreens.OPTIONS, OptionsScreen.class,
                GuiScreens.MULTIPLAYER_SELECT, MultiplayerScreen.class,
                GuiScreens.SINGLEPLAYER_SELECT, SelectWorldScreen.class,
                GuiScreens.LANGUAGE_SELECT, LanguageOptionsScreen.class,
                GuiScreens.PAUSE_MENU, GameMenuScreen.class,
                GuiScreens.REALMS_SCREEN, RealmsMainScreen.class,
                GuiScreens.IN_GAME, InGameHud.class
        );
    }
}
