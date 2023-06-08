package dev.lynith.onenineteenfour.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.impl.ScreenWrapper;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import dev.lynith.core.versions.renderer.MCScreen;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer implements IRenderer {

    private PoseStack matrixStack = null;

    @Override
    public void rect(int x, int y, int width, int height, ZabuColor color) {
        GuiComponent.fill(matrixStack, x, y, x + width, y + height, color.toHex());
    }

    @Override
    public void circle(int x, int y, int radius, ZabuColor color) {

    }

    @Override
    public void line(int x1, int y1, int x2, int y2, ZabuColor color) {

    }

    @Override
    public void text(String text, int x, int y, ZabuColor color, boolean shadow) {
        if (shadow) {
            Minecraft.getInstance().font.drawShadow(matrixStack, text, x, y, color.toHex());
        } else {
            Minecraft.getInstance().font.draw(matrixStack, text, x, y, color.toHex());
        }
    }

    @Override
    public int getTextHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public int getTextWidth(String text) {
        return Minecraft.getInstance().font.width(text);
    }

    @Override
    public void image(String path, int x, int y, int width, int height) {

    }

    @Override
    public int getWindowWidth() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    @Override
    public int getWindowHeight() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    @Override
    public int getScaleFactor() {
        System.out.println(Minecraft.getInstance().getWindow().getGuiScale());
        return (int) Minecraft.getInstance().getWindow().getGuiScale();
    }

    @Getter
    private GuiScreens currentScreen;

    @Override
    public void setCurrentScreen(MCScreen screen) {
        this.currentScreen = screen.getType();

        Minecraft.getInstance().setScreen(toGuiScreen(screen));
    }

    @Override
    public void setCurrentScreen(GuiScreens screen, Object... _args) {
        this.currentScreen = screen;

        try {
            List<Object> arguments = new ArrayList<>(Arrays.asList(_args));

            for (Object arg : arguments) {
                // Set any GuiScreens to their respective classes
                if (arg instanceof GuiScreens) {
                    int index = arguments.indexOf(arg);

                    // Create a new instance of the class and set it in the list
                    Object clazz = ((Class<?>) ClientStartup.getInstance().getBridge().getGame().getGuiScreens().get(arg)).newInstance();
                    arguments.set(index, clazz);
                }
            }

            // Special condition for the options screen because it requires the game settings
            if (screen == GuiScreens.OPTIONS_SCREEN) {
                Minecraft.getInstance().setScreen(new OptionsScreen((Screen) arguments.get(0), Minecraft.getInstance().options));
                return;
            }

            // Get the argument types
            Class<?>[] argTypes = new Class[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                Class<?> clazz = arguments.get(i).getClass();

                // If the class is a subclass of GuiScreen, set it to GuiScreen
                if (clazz.getSuperclass() != null && clazz.getSuperclass() == Screen.class) {
                    clazz = clazz.getSuperclass();
                }

                argTypes[i] = clazz;
            }

            // Get the GuiScreen class to display
            Class<?> clazz = (Class<?>) ClientStartup.getInstance().getBridge().getGame().getGuiScreens().get(screen);

            // Here is the instance of the GuiScreen, with the passed arguments
            Screen instance = (Screen) clazz.getConstructor(argTypes).newInstance(arguments.toArray());

            Minecraft.getInstance().setScreen(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Screen toGuiScreen(MCScreen screen) {
        return new Screen(Component.empty()) {
            @Override
            public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
                matrixStack = matrices;
                screen.render(mouseX, mouseY, delta);
                super.render(matrices, mouseX, mouseY, delta);
            }

            @Override
            protected void init() {
                screen.init();
                super.init();
            }

            @Override
            public void resize(Minecraft client, int width, int height) {
                screen.onResize(width, height);
                super.resize(client, width, height);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                screen.mouseClicked((int) mouseX, (int) mouseY, button);
                return super.mouseClicked(mouseX, mouseY, button);
            }

            @Override
            public void onClose() {
                screen.onClosed();
                super.onClose();
            }

            @Override
            public boolean mouseReleased(double mouseX, double mouseY, int button) {
                screen.mouseReleased((int) mouseX, (int) mouseY, button);
                return super.mouseReleased(mouseX, mouseY, button);
            }

            @Override
            public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
                screen.mouseClickedMoved((int) mouseX, (int) mouseY);
                return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
            }

            @Override
            public boolean charTyped(char chr, int modifiers) {
                screen.keyTyped(chr, modifiers);
                return super.charTyped(chr, modifiers);
            }
        };
    }

}
