package dev.lynith.oneeightnine;

import com.mojang.realmsclient.RealmsMainScreen;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiInventory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Game implements IGame {

    @Override
    public int getFps() {
        return Minecraft.getDebugFPS();
    }

    @Override
    public File getGameDir() {
        return Minecraft.getMinecraft().mcDataDir;
    }

    @Override
    public Map<GuiScreens, Object> getGuiScreens() {
        Map<GuiScreens, Object> map = new HashMap<>();

        map.put(GuiScreens.OPTIONS_SCREEN, GuiOptions.class);
        map.put(GuiScreens.MAIN_MENU, GuiMainMenu.class);
        map.put(GuiScreens.INVENTORY, GuiInventory.class);
        map.put(GuiScreens.SINGLEPLAYER_SELECT, GuiSelectWorld.class);
        map.put(GuiScreens.MULTIPLAYER_SELECT, GuiMultiplayer.class);
        map.put(GuiScreens.LANGUAGE_SELECT, GuiLanguage.class);
        map.put(GuiScreens.PAUSE_MENU, GuiIngameMenu.class);
        map.put(GuiScreens.REALMS_SCREEN, RealmsMainScreen.class);
        map.put(GuiScreens.IN_GAME, GuiIngame.class);

        return map;
    }
}
