package dev.lynith.oneeightnine.gui;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.bridge.gui.MCScreen;
import dev.lynith.core.ui.screens.MainMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Renderer implements IRenderer {

    @Override
    public @NotNull HashMap<Class<?>, GuiType> getScreenMap() {
        HashMap<Class<?>, GuiType> map = new HashMap<>();
        map.put(TitleScreen.class, GuiType.MAIN_MENU);
        map.put(MultiplayerScreen.class, GuiType.MULTIPLAYER_SELECTOR);
        map.put(SelectWorldScreen.class, GuiType.SINGLEPLAYER_SELECTOR);
        map.put(GameMenuScreen.class, GuiType.PAUSE_MENU);
        map.put(SettingsScreen.class, GuiType.OPTIONS);

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
    public boolean setScreen(GuiType screen, Object... args) {
        try {
            Class<?> clazz = null;

            for (Map.Entry<Class<?>, GuiType> entry : getScreenMap().entrySet()) {
                if (entry.getValue().equals(screen)) {
                    clazz = entry.getKey();
                    break;
                }
            }

            if (clazz == null) {
                return false;
            }

            Class<?>[] paramTypes = new Class[args.length];
            Constructor<?> constructor = null;

            for (Constructor<?> c : clazz.getConstructors()) {
                if (c.getParameterCount() == args.length) {
                    constructor = c;
                    break;
                }
            }

            if (constructor == null) {
                return false;
            }

            Class<?>[] constructorParamTypes = constructor.getParameterTypes();

            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof dev.lynith.core.ui.components.Screen) {
                    args[i] = ((dev.lynith.core.ui.components.Screen) args[i]).toMCScreen();
                }

                if (args[i] instanceof MCScreen) {
                    args[i] = toMCScreen((MCScreen) args[i]);
                }

                if (args[i] == null) {
                    paramTypes[i] = null;
                    continue;
                }

                if (constructorParamTypes[i].isAssignableFrom(args[i].getClass())) {
                    paramTypes[i] = constructorParamTypes[i];
                    continue;
                }

                if (constructorParamTypes[i].isAssignableFrom(args[i].getClass().getSuperclass())) {
                    paramTypes[i] = constructorParamTypes[i];
                    continue;
                }

                return false;
            }

            Object instance = clazz.getConstructor(paramTypes).newInstance(args);
            MinecraftClient.getInstance().setScreen((Screen) instance);
            return true;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean setScreen(dev.lynith.core.ui.components.Screen screen, Object... args) {
        MinecraftClient.getInstance().setScreen(
            screen == null
                ? null
                : toMCScreen(screen.toMCScreen())
        );
        return true;
    }

    @Override
    public int getWindowWidth() {
        return new Window(MinecraftClient.getInstance()).getWidth() * getScaleFactor();
    }

    @Override
    public int getWindowHeight() {
        return new Window(MinecraftClient.getInstance()).getHeight() * getScaleFactor();
    }

    @Override
    public int getScaleFactor() {
        return new Window(MinecraftClient.getInstance()).getScaleFactor();
    }

    private Screen toMCScreen(MCScreen screen) {
        return new Screen() {

            boolean initiated = false;

            @Override
            public void render(int i, int j, float f) {
                super.render(i, j, f);
                screen.render(i, j, f);
            }

            @Override
            public void init() {
                super.init();

                if (!initiated) {
                    initiated = true;
                    screen.init();
                }
            }

            @Override
            protected void keyPressed(char c, int i) {
                super.keyPressed(c, i);
                screen.keyPressed(c, i);
            }

            @Override
            protected void mouseClicked(int i, int j, int k) {
                i *= getScaleFactor();
                j *= getScaleFactor();

                super.mouseClicked(i, j, k);
                screen.mouseClicked(i, j, k);
            }

            @Override
            protected void mouseDragged(int i, int j, int k, long l) {
                super.mouseDragged(i, j, k, l);
                screen.mouseDragged(i, j, k, l);
            }

            @Override
            protected void mouseReleased(int i, int j, int k) {
                i *= getScaleFactor();
                j *= getScaleFactor();

                super.mouseReleased(i, j, k);
                screen.mouseReleased(i, j, k);
            }

            @Override
            public boolean shouldPauseGame() {
                return screen.shouldPauseGame();
            }

            @Override
            public void removed() {
                super.removed();
                screen.closed();
            }

            @Override
            public void resize(MinecraftClient minecraftClient, int i, int j) {
                super.resize(minecraftClient, i, j);
                screen.resized();
            }
        };
    }

    @Override
    public void displayOptionsScreen(dev.lynith.core.ui.components.@NotNull Screen parent) {
        setScreen(GuiType.OPTIONS, parent, MinecraftClient.getInstance().options);
    }

    @Override
    public void displaySingleplayerSelectorScreen(dev.lynith.core.ui.components.@NotNull Screen parent) {
        setScreen(GuiType.SINGLEPLAYER_SELECTOR, parent);
    }

    @Override
    public void displayMultiplayerSelectorScreen(dev.lynith.core.ui.components.@NotNull Screen parent) {
        setScreen(GuiType.MULTIPLAYER_SELECTOR, parent);
    }
}
