package dev.lynith.oneeightnine.renderer;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import dev.lynith.core.versions.renderer.MCScreen;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer implements IRenderer {

    private final DrawableHelper gui = new DrawableHelper();

    @Override
    public void rect(int x, int y, int width, int height, ZabuColor color) {
        DrawableHelper.fill(x, y, x + width, y + height, color.toHex());
    }

    @Override
    public void circle(int x, int y, int radius, ZabuColor color) {

    }

    @Override
    public void line(int x1, int y1, int x2, int y2, ZabuColor color) {
//        Tessellator tessellator = Tessellator.getInstance();
//        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
//        GlStateManager.enableBlend();
//        GlStateManager.disableTexture2D();
//        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//        GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
//        worldRenderer.begin(1, DefaultVertexFormats.POSITION);
//        worldRenderer.pos(x1, y1, 0.0D).endVertex();
//        worldRenderer.pos(x2, y2, 0.0D).endVertex();
//        tessellator.draw();
//        GlStateManager.enableTexture2D();
//        GlStateManager.disableBlend();
    }

    @Override
    public void text(String text, int x, int y, ZabuColor color, boolean shadow) {
        MinecraftClient.getInstance().textRenderer.draw(text, x, y, color.toHex(), shadow);
    }

    @Override
    public int getTextWidth(String text) {
        return MinecraftClient.getInstance().textRenderer.getStringWidth(text);
    }

    @Override
    public int getTextHeight() {
        return MinecraftClient.getInstance().textRenderer.fontHeight;
    }

    @Override
    public void image(String path, int x, int y, int width, int height) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier(path));
        gui.drawTexture(x, y, 0, 0, width, height);
    }

    @Override
    public int getWindowWidth() {
        return MinecraftClient.getInstance().width;
    }

    @Override
    public int getWindowHeight() {
        return MinecraftClient.getInstance().height;
    }

    @Override
    public int getScaleFactor() {
        return new Window(MinecraftClient.getInstance()).getScaleFactor();
    }

    @Getter
    private GuiScreens currentScreen;

    @Override
    public void setCurrentScreen(MCScreen screen) {
        this.currentScreen = screen.getType();

        MinecraftClient.getInstance().setScreen(toGuiScreen(screen));
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
            if (screen == GuiScreens.OPTIONS_SCREEN) {
                MinecraftClient.getInstance().setScreen(new SettingsScreen((Screen) arguments.get(0), MinecraftClient.getInstance().options));
                return;
            }

            // Get the argument types
            Class<?>[] argTypes = new Class[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                Class<?> clazz = arguments.get(i).getClass();

                // If the class is a subclass of GuiScreen, set it to GuiScreen
                if (clazz.getSuperclass() != null && clazz.getSuperclass().equals(Screen.class)) {
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
        return new Screen() {
            @Override
            public void render(int i, int j, float f) {
                screen.render(i, j, f);
                super.render(i, j, f);
            }

            @Override
            public void init(MinecraftClient minecraftClient, int i, int j) {
                screen.init();
                super.init(minecraftClient, i, j);
            }

//            @Override
//            public void init() {
//                screen.update();
//                super.init();
//            }

            @Override
            public void removed() {
                screen.onClosed();
                super.removed();
            }

            @Override
            public void resize(MinecraftClient MinecraftClient, int i, int j) {
                screen.onResize(i, j);
                super.resize(MinecraftClient, i, j);
            }

            @Override
            protected void mouseClicked(int i, int j, int k) {
                screen.mouseClicked(i, j, k);
                super.mouseClicked(i, j, k);
            }

            @Override
            protected void mouseDragged(int i, int j, int k, long l) {
                screen.mouseClickedMoved(i, j);
                super.mouseDragged(i, j, k, l);
            }

            @Override
            protected void mouseReleased(int i, int j, int k) {
                screen.mouseReleased(i, j, k);
                super.mouseReleased(i, j, k);
            }

            @Override
            protected void keyPressed(char c, int i) {
                screen.keyTyped(c, i);
                super.keyPressed(c, i);
            }
        };
    }

}
