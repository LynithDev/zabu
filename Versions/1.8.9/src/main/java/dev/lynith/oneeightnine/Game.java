package dev.lynith.oneeightnine;

import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.IGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiInventory;

import java.util.HashMap;
import java.util.Map;

public class Game implements IGame {

    @Override
    public int getFps() {
        return Minecraft.getDebugFPS();
    }

    @Override
    public Map<GuiScreens, Object> getGuiScreens() {
        Map<GuiScreens, Object> map = new HashMap<>();

        map.put(GuiScreens.OPTIONS_SCREEN, GuiOptions.class);
        map.put(GuiScreens.MAIN_MENU, GuiMainMenu.class);
        map.put(GuiScreens.INVENTORY, GuiInventory.class);
        map.put(GuiScreens.SINGLEPLAYER_SELECT, GuiSelectWorld.class);
        map.put(GuiScreens.MULTIPLAYER_SELECT, GuiMultiplayer.class);

        return map;
    }
}
