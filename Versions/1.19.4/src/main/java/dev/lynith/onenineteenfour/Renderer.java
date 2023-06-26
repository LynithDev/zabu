package dev.lynith.onenineteenfour;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.ScreenWrapper;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import dev.lynith.core.versions.renderer.MCScreen;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer implements IRenderer {

    public static MatrixStack matrixStack = null;

    @Override
    public void rect(int x, int y, int width, int height, ZabuColor color) {
        DrawableHelper.fill(matrixStack, x, y, x + width, y + height, color.toHex());
    }

    @Override
    public void circle(int x, int y, int radius, ZabuColor color) {

    }

    @Override
    public void line(int x1, int y1, int x2, int y2, ZabuColor color) {

    }

    @Override
    public void textf(String text, int x, int y, ZabuColor color) {
        MinecraftClient.getInstance().textRenderer.draw(matrixStack, text, x, y, color.toHex());
    }

    @Override
    public void text(String text, int x, int y, ZabuColor color, boolean shadow) {
        if (shadow) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrixStack, text, x, y, color.toHex());
        } else {
            MinecraftClient.getInstance().textRenderer.draw(matrixStack, text, x, y, color.toHex());
        }
    }

    @Override
    public int getTextHeight() {
        return MinecraftClient.getInstance().textRenderer.fontHeight;
    }

    @Override
    public int getTextWidth(String text) {
        return MinecraftClient.getInstance().textRenderer.getWidth(text);
    }

    @Override
    public void image(String path, int x, int y, int width, int height) {

    }

    @Override
    public int getWindowWidth() {
        return MinecraftClient.getInstance().getWindow().getScaledWidth();
    }

    @Override
    public int getWindowHeight() {
        return MinecraftClient.getInstance().getWindow().getScaledHeight();
    }

    @Override
    public int getScaleFactor() {
        return (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
    }

    private GuiScreens currentScreen;

    @Override
    public GuiScreens getCurrentScreenType() {
        return this.currentScreen;
    }

    @Override
    public void setCurrentScreenType(GuiScreens screen) {
        this.currentScreen = screen;
    }

    @Override
    public void setCurrentScreen(dev.lynith.core.ui.Screen<?, ?> screen) {
        this.currentScreen = screen.type();

        MinecraftClient.getInstance().setScreen(toGuiScreen(new ScreenWrapper(screen)));
    }

    @Override
    public void setCurrentScreen(GuiScreens screen, Object... _args) {
        this.currentScreen = screen;

        if (screen == GuiScreens.UNKNOWN) return;

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
            if (screen == GuiScreens.OPTIONS) {
                MinecraftClient.getInstance().setScreen(new OptionsScreen((Screen) arguments.get(0), MinecraftClient.getInstance().options));
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

            MinecraftClient.getInstance().setScreen(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Screen toGuiScreen(MCScreen screen) {
        return new Screen(Text.empty()) {
            @Override
            public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
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
            public void resize(MinecraftClient client, int width, int height) {
                screen.onResize(width, height);
                super.resize(client, width, height);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                screen.mouseClicked((int) mouseX, (int) mouseY, button);
                return super.mouseClicked(mouseX, mouseY, button);
            }

            @Override
            public void close() {
                screen.onClosed();
                super.close();
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
