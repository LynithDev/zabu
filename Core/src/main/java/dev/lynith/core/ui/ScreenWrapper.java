package dev.lynith.core.ui;

import dev.lynith.core.ui.callbacks.impl.*;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.renderer.MCScreen;
import lombok.Getter;
import lombok.experimental.Accessors;

public class ScreenWrapper extends MCScreen {

    @Getter @Accessors(fluent = false)
    private final GuiScreens type;

    @Getter
    private final Screen<?, ?> screen;

    public ScreenWrapper(Screen<?, ?> screen) {
        this(GuiScreens.UNKNOWN, screen);
    }

    public ScreenWrapper(GuiScreens type, Screen<?, ?> screen) {
        this.type = type;
        this.screen = screen;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.screen.callCallbacks(MouseEnter.class, mouseX, mouseY);
        if (this.dragging) this.screen.callCallbacks(MouseDrag.class, mouseX, mouseY);

        screen.render(renderer);
    }

    @Override
    public void init() {
        this.screen.callCallbacks(Destroyed.class);
        this.screen.init();

        int width = renderer.getWindowWidth();
        int height = renderer.getWindowHeight();

        if (renderer.getScaleFactor() > 1) {
            height /= renderer.getScaleFactor();
            width /= renderer.getScaleFactor();
        }

        this.onResize(width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void onClosed() {
        this.screen.callCallbacks(Destroyed.class);
    }

    @Override
    public void onResize(int width, int height) {
        this.screen.callCallbacks(Resized.class, width, height);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.screen.callCallbacks(KeyTyped.class, keyCode);
    }

    private boolean clicked = false;
    private boolean dragging = false;

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.clicked = true;
        this.dragging = true;
        this.screen.callCallbacks(MousePress.class, mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (this.clicked) {
            this.clicked = false;
            this.screen.callCallbacks(MouseClick.class, mouseX, mouseY);
        }
        this.dragging = false;
        this.screen.callCallbacks(MouseRelease.class, mouseX, mouseY);
    }

    @Override
    public void mouseClickedMoved(int mouseX, int mouseY) {
        // Replaced with custom drag implementation. This one is choppier as it gets called less often
//        this.screen.callCallbacks(MouseDrag.class, mouseX, mouseY);
    }
}
