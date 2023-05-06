package dev.lynith.Core.ui.impl;

import dev.lynith.Core.ClientStartup;
import dev.lynith.Core.utils.GuiScreens;
import dev.lynith.Core.versions.renderer.Screen;
import lombok.Getter;

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
        this.screen.init();
    }

    @Override
    public void update() {
        this.screen.update();
    }

    @Override
    public void onClosed() {
        this.screen.getOnDestroy().handle();
    }

    @Override
    public void onResize(int width, int height) {
        this.screen.getOnResize().handle(this.screen, width, height);
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
