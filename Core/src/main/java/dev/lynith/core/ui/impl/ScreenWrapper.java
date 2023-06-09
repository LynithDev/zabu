package dev.lynith.core.ui.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.VersionUtils;
import dev.lynith.core.versions.renderer.MCScreen;
import lombok.Getter;

/**
 * Wrapper for {@link Screen} to be used as a {@link MCScreen}
 * This should be used for screens using the UI system
 */
public class ScreenWrapper extends MCScreen {

    @Getter
    private final GuiScreens type;

    @Getter
    private final Screen screen;

    public ScreenWrapper(Screen screen) {
        this(GuiScreens.UNKNOWN, screen);
    }

    public ScreenWrapper(GuiScreens type, Screen screen) {
        this.type = type;
        this.screen = screen;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.screen.render(ClientStartup.getInstance().getBridge().getRenderer());
        this.screen.callCallbacks(Component.EnterCallback.class, mouseX, mouseY);
        if (this.dragging) this.screen.callCallbacks(Component.DragCallback.class, mouseX, mouseY);
    }

    @Override
    public void init() {
        this.screen.clearChildren();
        this.screen.init();

        int width = ClientStartup.getInstance().getBridge().getRenderer().getWindowWidth();
        int height = ClientStartup.getInstance().getBridge().getRenderer().getWindowHeight();

        if (VersionUtils.getMinorVersion(ClientStartup.getInstance().getBridge().getVersion()) <= 16) {
            height /= ClientStartup.getInstance().getBridge().getRenderer().getScaleFactor();
            width /= ClientStartup.getInstance().getBridge().getRenderer().getScaleFactor();
        }

        this.onResize(width, height);
    }

    @Override
    public void onClosed() {
        this.screen.callCallbacks(Component.DestroyCallback.class);
        ClientStartup.getInstance().getBridge().getRenderer().setCurrentScreen(GuiScreens.UNKNOWN);
    }

    @Override
    public void onResize(int width, int height) {
        this.screen.callCallbacks(Screen.ResizeCallback.class, width, height);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.screen.callCallbacks(Component.KeyTypedCallback.class, typedChar, keyCode);
    }

    private boolean clicked = false;
    private boolean dragging = false;

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.clicked = true;
        this.dragging = true;
        this.screen.callCallbacks(Component.PressCallback.class, mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (this.clicked) {
            this.clicked = false;
            this.screen.callCallbacks(Component.ClickCallback.class, mouseX, mouseY);
        }
        this.dragging = false;
        this.screen.callCallbacks(Component.ReleaseCallback.class, mouseX, mouseY);
    }

}
