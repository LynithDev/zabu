package dev.lynith.oneeightnine;

import com.mojang.realmsclient.RealmsMainScreen;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IGame;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Game implements IGame {

    @Override
    public int getFps() {
        return MinecraftClient.getCurrentFps();
    }

    @Override
    public File getGameDir() {
        return MinecraftClient.getInstance().runDirectory;
    }

    @Override
    public Map<GuiScreens, Object> getGuiScreens() {

        Map<GuiScreens, Object> map = new HashMap<>();

        map.put(GuiScreens.OPTIONS_SCREEN, SettingsScreen.class);
        map.put(GuiScreens.MAIN_MENU, TitleScreen.class);
        map.put(GuiScreens.INVENTORY, InventoryScreen.class);
        map.put(GuiScreens.SINGLEPLAYER_SELECT, SelectWorldScreen.class);
        map.put(GuiScreens.MULTIPLAYER_SELECT, MultiplayerScreen.class);
        map.put(GuiScreens.LANGUAGE_SELECT, LanguageOptionsScreen.class);
        map.put(GuiScreens.PAUSE_MENU, GameMenuScreen.class);
        map.put(GuiScreens.REALMS_SCREEN, RealmsMainScreen.class);
//        map.put(GuiScreens.IN_GAME, InGameHud.class);

        return map;
    }
}
