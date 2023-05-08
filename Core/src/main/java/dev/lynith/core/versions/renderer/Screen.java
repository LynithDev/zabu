package dev.lynith.core.versions.renderer;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.GuiScreens;

public abstract class Screen {

    protected final IRenderer renderer = ClientStartup.getInstance().getBridge().getRenderer();

    public abstract GuiScreens getType();

    public void render(int mouseX, int mouseY, float partialTicks) {};
    public void init() {}
    public void update() {}

    public void onClosed() {}
    public void onResize(int width, int height) {}

    public void keyTyped(char typedChar, int keyCode) {};

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {};
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {};
    public void mouseClickedMoved(int mouseX, int mouseY) {};

}
