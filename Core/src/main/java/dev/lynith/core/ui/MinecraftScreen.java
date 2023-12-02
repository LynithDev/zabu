package dev.lynith.core.ui;

public abstract class MinecraftScreen {

    public abstract void render(int mouseX, int mouseY, float delta);
    public abstract void init();

    public void closed() {}

    public void mouseClicked(int mouseX, int mouseY, int button) {}
    public void mouseReleased(int mouseX, int mouseY, int button) {}
    public void mouseClickedMoved(int mouseX, int mouseY, int button, long time) {}

    public void keyPressed(char typedChar, int keyCode) {}

    public boolean shouldPauseGame() {
        return true;
    }

}