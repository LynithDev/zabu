package dev.lynith.onenineteenfour.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.lynith.Core.ClientStartup;
import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.utils.ZabuColor;
import dev.lynith.Core.versions.renderer.IRenderer;
import dev.lynith.Core.versions.renderer.Screen;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;

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
    public int getDisplayWidth() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    @Override
    public int getDisplayHeight() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    @Override
    public int getScaleFactor() {
        return Minecraft.getInstance().options.guiScale().get();
    }

    @Getter
    private GuiScreens currentScreen;

    @Override
    public void setCurrentScreen(Screen screen) {
        this.currentScreen = screen.getType();

        Minecraft.getInstance().setScreen(toGuiScreen(screen));
    }

    @Override
    public void setCurrentScreen(GuiScreens screen) {
        this.currentScreen = null;
        try {
            Object clazz = ClientStartup.getInstance().getBridge().getGame().getGuiScreens().get(screen).getClass().newInstance();
            Minecraft.getInstance().setScreen((net.minecraft.client.gui.screens.Screen) clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public net.minecraft.client.gui.screens.Screen toGuiScreen(Screen screen) {
        return new net.minecraft.client.gui.screens.Screen(Component.empty()) {
            @Override
            public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
                matrixStack = matrices;
                screen.render(mouseX, mouseY, delta);
            }

            @Override
            protected void init() {
                screen.init();
            }

            @Override
            public void resize(Minecraft client, int width, int height) {
                screen.onResize(width, height);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                screen.mouseClicked((int) mouseX, (int) mouseY, button);
                return true;
            }

            @Override
            public boolean mouseReleased(double mouseX, double mouseY, int button) {
                screen.mouseReleased((int) mouseX, (int) mouseY, button);
                return true;
            }

            @Override
            public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
                screen.mouseClickedMoved((int) mouseX, (int) mouseY);
                return true;
            }

            @Override
            public boolean charTyped(char chr, int modifiers) {
                screen.keyTyped(chr, modifiers);
                return true;
            }
        };
    }

}
