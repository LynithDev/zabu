package dev.lynith.core.versions.renderer;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.GuiScreens;

public abstract class MCScreen {

    protected final IRenderer renderer = ClientStartup.getInstance().getBridge().getRenderer();

    public abstract GuiScreens getType();

    abstract public void render(int mouseX, int mouseY, float partialTicks);
    abstract public void init();
    abstract public void update();

    abstract public void onClosed();
    abstract public void onResize(int width, int height);

    abstract public void keyTyped(char typedChar, int keyCode);

    abstract public void mouseClicked(int mouseX, int mouseY, int mouseButton);
    abstract public void mouseReleased(int mouseX, int mouseY, int mouseButton);
    abstract public void mouseClickedMoved(int mouseX, int mouseY);

}
