package dev.lynith.core.ui.screens;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.Screen;
import dev.lynith.core.utils.NanoVGHelper;

public class MainMenu extends Screen {

    @Override
    public void render(IRenderer renderer, int mouseX, int mouseY, float delta) {
        NanoVGHelper.createFrame();
        NanoVGHelper.rectangle(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), NanoVGHelper.createColor(0, 0, 0, 255));
        NanoVGHelper.circle(150, 150, 100, NanoVGHelper.createColor(255, 0, 0, 255));
        NanoVGHelper.endFrame();
    }

    @Override
    public void init() {

    }

}
