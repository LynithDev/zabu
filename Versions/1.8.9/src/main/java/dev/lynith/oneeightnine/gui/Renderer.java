package dev.lynith.oneeightnine.gui;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.MinecraftScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Renderer implements IRenderer {

    @Override
    public HashMap<Class<?>, GuiType> getScreenMap() {
        HashMap<Class<?>, GuiType> map = new HashMap<>();
        map.put(TitleScreen.class, GuiType.MAIN_MENU);
        map.put(MultiplayerScreen.class, GuiType.MULTIPLAYER_SELECTOR);
        map.put(SelectWorldScreen.class, GuiType.SINGLEPLAYER_SELECTOR);
        map.put(GameMenuScreen.class, GuiType.PAUSE_MENU);

        return map;
    }

    @Override
    public GuiType getCurrentScreen() {
        GuiType type = GuiType.UNKNOWN;

        for (Map.Entry<Class<?>, GuiType> entry : getScreenMap().entrySet()) {
            if (MinecraftClient.getInstance().currentScreen.getClass().equals(entry.getKey())) {
                type = entry.getValue();
                break;
            }
        }

        return type;
    }

    @Override
    public boolean displayScreen(GuiType screen, Object... args) {
        try {
            Class<?> clazz = null;

            for (Map.Entry<Class<?>, GuiType> entry : getScreenMap().entrySet()) {
                if (entry.getValue().equals(screen)) {
                    clazz = entry.getKey();
                    break;
                }
            }

            Class<?>[] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }

            if (clazz != null) {
                Object instance = clazz.getConstructor(paramTypes).newInstance(args);
                MinecraftClient.getInstance().setScreen((Screen) instance);
                return true;
            }
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean displayScreen(dev.lynith.core.ui.components.Screen screen, Object... args) {
        return false;
    }

    private Screen toMCScreen(MinecraftScreen screen) {
        return new Screen() {

            @Override
            public void render(int i, int j, float f) {
                screen.render(i, j, f);

                super.render(i, j, f);
            }

            @Override
            public void init() {
                screen.init();
                super.init();
            }

            @Override
            protected void keyPressed(char c, int i) {
                screen.keyPressed(c, i);
                super.keyPressed(c, i);
            }

            @Override
            protected void mouseClicked(int i, int j, int k) {
                screen.mouseClicked(i, j, k);
                super.mouseClicked(i, j, k);
            }

            @Override
            protected void mouseDragged(int i, int j, int k, long l) {
                screen.mouseClickedMoved(i, j, k, l);
                super.mouseDragged(i, j, k, l);
            }

            @Override
            protected void mouseReleased(int i, int j, int k) {
                screen.mouseReleased(i, j, k);
                super.mouseReleased(i, j, k);
            }

            @Override
            public boolean shouldPauseGame() {
                return screen.shouldPauseGame();
            }

        };
    }

    /*
        #### IRendererHelper ####
    */

    @Override
    public void rect(int x, int y, int width, int height, int color) {
        DrawableHelper.fill(x, y, x + width, y + height, color);
    }

    @Override
    public void text(String text, int x, int y, int color) {
        MinecraftClient.getInstance().textRenderer.draw(text, x, y, color);
    }

    @Override
    public void text(String text, int x, int y, int color, boolean shadow) {
        MinecraftClient.getInstance().textRenderer.draw(text, x, y, color, shadow);
    }

    @Override
    public int textWidth(String text) {
        return MinecraftClient.getInstance().textRenderer.getStringWidth(text);
    }

    @Override
    public int fontHeight() {
        return MinecraftClient.getInstance().textRenderer.fontHeight;
    }
}