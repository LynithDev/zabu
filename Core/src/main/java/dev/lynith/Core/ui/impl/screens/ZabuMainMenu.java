package dev.lynith.Core.ui.impl.screens;

import dev.lynith.Core.ui.impl.UIScreen;
import dev.lynith.Core.ui.impl.components.Button;
import dev.lynith.Core.utils.ZabuColor;
import dev.lynith.Core.versions.renderer.IRenderer;

public class ZabuMainMenu extends UIScreen {

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), ZabuColor.from(0));
        super.render(renderer);
    }

    @Override
    public void init() {
        Button button = new Button();

        button.setText("View UI information");

        addChild(button);

        super.init();
    }
}
