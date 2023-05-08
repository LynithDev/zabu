package dev.lynith.oneeightnine.renderer;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import dev.lynith.core.versions.renderer.MCScreen;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer implements IRenderer {

    private final Gui gui = new Gui();

    @Override
    public void rect(int x, int y, int width, int height, ZabuColor color) {
        Gui.drawRect(x, y, x + width, y + height, color.toHex());
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
        Minecraft.getMinecraft().fontRendererObj.drawString(text, x, y, color.toHex(), shadow);
    }

    @Override
    public int getTextWidth(String text) {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
    }

    @Override
    public int getTextHeight() {
        return Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
    }

    @Override
    public void image(String path, int x, int y, int width, int height) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(path));
        gui.drawTexturedModalRect(x, y, 0, 0, width, height);
    }

    @Override
    public int getWindowWidth() {
        return Minecraft.getMinecraft().displayWidth;
    }

    @Override
    public int getWindowHeight() {
        return Minecraft.getMinecraft().displayHeight;
    }

    @Override
    public int getScaleFactor() {
        return new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();
    }

    @Getter
    private GuiScreens currentScreen;

    @Override
    public void setCurrentScreen(MCScreen screen) {
        this.currentScreen = screen.getType();

        Minecraft.getMinecraft().displayGuiScreen(toGuiScreen(screen));
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
                Minecraft.getMinecraft().displayGuiScreen(new GuiOptions((GuiScreen) arguments.get(0), Minecraft.getMinecraft().gameSettings));
                return;
            }

            // Get the argument types
            Class<?>[] argTypes = new Class[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                Class<?> clazz = arguments.get(i).getClass();

                // If the class is a subclass of GuiScreen, set it to GuiScreen
                if (clazz.getSuperclass() != null && clazz.getSuperclass() == GuiScreen.class) {
                    clazz = clazz.getSuperclass();
                }

                argTypes[i] = clazz;
            }

            // Get the GuiScreen class to display
            Class<?> clazz = (Class<?>) ClientStartup.getInstance().getBridge().getGame().getGuiScreens().get(screen);

            // Here is the instance of the GuiScreen, with the passed arguments
            GuiScreen instance = (GuiScreen) clazz.getConstructor(argTypes).newInstance(arguments.toArray());

            Minecraft.getMinecraft().displayGuiScreen(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuiScreen toGuiScreen(MCScreen screen) {
        return new GuiScreen() {
            @Override
            public void drawScreen(int i, int j, float f) {
                screen.render(i, j, f);
            }

            @Override
            public void initGui() {
                screen.init();
            }

            @Override
            public void updateScreen() {
                screen.update();
            }

            @Override
            public void onGuiClosed() {
                screen.onClosed();
            }

            @Override
            public void onResize(Minecraft minecraft, int i, int j) {
                screen.onResize(i, j);
            }

            @Override
            protected void mouseClicked(int i, int j, int k) {
                screen.mouseClicked(i, j, k);
            }

            @Override
            protected void mouseClickMove(int i, int j, int k, long l) {
                screen.mouseClickedMoved(i, j);
            }

            @Override
            protected void mouseReleased(int i, int j, int k) {
                screen.mouseReleased(i, j, k);
            }

            @Override
            protected void keyTyped(char c, int i) {
                screen.keyTyped(c, i);
            }
        };
    }

}
