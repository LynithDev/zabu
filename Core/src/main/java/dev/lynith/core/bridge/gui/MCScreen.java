package dev.lynith.core.bridge.gui;

public class MCScreen {

    public void render(int mouseX, int mouseY, float delta) {}
    public void init() {}

    public void closed() {}
    public void keyPressed(char typedChar, int keyCode) {}
    public void mouseClicked(int mouseX, int mouseY, int button) {}
    public void mouseReleased(int mouseX, int mouseY, int button) {}
    public void mouseDragged(int mouseX, int mouseY, int button, float time) {}

    public boolean shouldPauseGame() { return false; }

    public void resized() {}
}
