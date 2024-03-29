package dev.lynith.onetwentytwo.gui;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.bridge.gui.MCScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
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
        map.put(OptionsScreen.class, GuiType.OPTIONS);

        return map;
    }

    @Override
    public GuiType getCurrentScreen() {
        GuiType type = GuiType.UNKNOWN;

        Screen screen = MinecraftClient.getInstance().currentScreen;
        if (screen == null) {
            return GuiType.INGAME;
        }

        for (Map.Entry<Class<?>, GuiType> entry : getScreenMap().entrySet()) {
            if (screen.getClass().equals(entry.getKey())) {
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
        if (screen == null) {
            return false;
        }

        MinecraftClient.getInstance().setScreen(toMCScreen(screen.toMCScreen()));
        return true;
    }

    @Override
    public int getWindowWidth() {
        return MinecraftClient.getInstance().getWindow().getScaledWidth() * getScaleFactor();
    }

    @Override
    public int getWindowHeight() {
        return MinecraftClient.getInstance().getWindow().getScaledHeight() * getScaleFactor();
    }

    @Override
    public int getScaleFactor() {
        return (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
    }

    private Screen toMCScreen(MCScreen screen) {
        return new Screen(Text.empty()) {

            boolean initiated = false;

            @Override
            public void render(DrawContext drawContext, int i, int j, float f) {
                super.render(drawContext, i, j, f);
                screen.render(i, j, f);
            }

            @Override
            public void init() {
                super.init();

                if (!initiated) {
                    screen.init();
                    initiated = true;
                }
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
                d *= getScaleFactor();
                e *= getScaleFactor();

                screen.mouseClicked((int) d, (int) e, i);
                return super.mouseClicked(d, e, i);
            }

            @Override
            public boolean mouseReleased(double d, double e, int i) {
                d *= getScaleFactor();
                e *= getScaleFactor();

                screen.mouseReleased((int) d, (int) e, i);
                return super.mouseReleased(d, e, i);
            }

            @Override
            public void resize(MinecraftClient minecraftClient, int i, int j) {
                super.resize(minecraftClient, i, j);
                screen.resized();
            }

            @Override
            public void removed() {
                super.removed();
                screen.closed();
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
