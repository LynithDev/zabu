package dev.lynith.core.ui.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.VersionUtils;
import dev.lynith.core.versions.renderer.Screen;
import lombok.Getter;

/**
 * Wrapper for {@link UIScreen} to be used as a {@link Screen}
 * This should be used for screens using the UI system
 */
public class ScreenWrapper extends Screen {

    @Getter
    private final GuiScreens type;

    @Getter
    private final UIScreen screen;

    public ScreenWrapper(GuiScreens type, UIScreen screen) {
        this.type = type;
        this.screen = screen;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.screen.render(ClientStartup.getInstance().getBridge().getRenderer());
        this.screen.getOnEnter().handle(mouseX, mouseY);
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
        this.screen.getOnDestroy().handle();
    }

    @Override
    public void onResize(int width, int height) {
        this.screen.getOnResize().handle(width, height);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.screen.getOnKeyTyped().handle(typedChar, keyCode);
    }

    private boolean clicked = false;

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.clicked = true;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (this.clicked) {
            this.clicked = false;
            this.screen.getOnClick().handle(mouseX, mouseY);
        }
    }

    @Override
    public void mouseClickedMoved(int mouseX, int mouseY) {
        this.screen.getOnDrag().handle(mouseX, mouseY);
    }

}
