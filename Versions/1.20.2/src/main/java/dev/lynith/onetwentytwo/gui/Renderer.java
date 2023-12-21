package dev.lynith.onetwentytwo.gui;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.bridge.gui.MCScreen;
import dev.lynith.core.ui.components.callbacks.ComponentMouseClicked;
import dev.lynith.core.ui.components.callbacks.ComponentMouseReleased;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;

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
    public void rect(int x, int y, int width, int height, int color) {

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
        Renderer renderer = this;

        MinecraftClient.getInstance().setScreen(toMCScreen(new MCScreen() {
            @Override
            public void render(int mouseX, int mouseY, float delta) {
                screen.wrappedRender(renderer, mouseX, mouseY, delta);
            }

            @Override
            public void init() {
                screen.wrappedInit();
            }

            @Override
            public void mouseClicked(int mouseX, int mouseY, int button) {
                screen.emit(ComponentMouseClicked.class, mouseX, mouseY, button);
            }

            @Override
            public void mouseReleased(int mouseX, int mouseY, int button) {
                screen.emit(ComponentMouseReleased.class, mouseX, mouseY, button);
            }
        }));
        return true;
    }

    @Override
    public int getWindowWidth() {
        return MinecraftClient.getInstance().getWindow().getWidth();
    }

    @Override
    public int getWindowHeight() {
        return MinecraftClient.getInstance().getWindow().getHeight();
    }

    private Screen toMCScreen(MCScreen screen) {
        return new Screen(Text.empty()) {

            @Override
            public void render(DrawContext drawContext, int i, int j, float f) {
                super.render(drawContext, i, j, f);
                screen.render(i, j, f);
            }

            @Override
            public void init() {
                screen.init();
                super.init();
            }

            @Override
            public boolean keyPressed(int i, int j, int k) {

                return super.keyPressed(i, j, k);
            }

            @Override
            public boolean shouldPause() {
                return screen.shouldPauseGame();
            }

            @Override
            public boolean mouseClicked(double d, double e, int i) {
                screen.mouseClicked((int) d, (int) e, i);
                return super.mouseClicked(d, e, i);
            }

            @Override
            public boolean mouseReleased(double d, double e, int i) {
                screen.mouseReleased((int) d, (int) e, i);
                return super.mouseReleased(d, e, i);
            }
        };
    }

}
