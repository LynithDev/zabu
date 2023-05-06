package dev.lynith.oneeightnine;

import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.Tuple;
import dev.lynith.Core.versions.IGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
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

        return map;
    }
}
