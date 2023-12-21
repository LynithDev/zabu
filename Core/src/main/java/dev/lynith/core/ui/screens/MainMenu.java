package dev.lynith.core.ui.screens;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.Screen;
import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.styles.Style;
import dev.lynith.core.ui.styles.impl.Color;
import dev.lynith.core.utils.NanoVGHelper;

public class MainMenu extends Screen {

    @Override
    public void render(IRenderer renderer, int mouseX, int mouseY, float delta) {

        rectangle(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), new Color(0, 0));
        circle(150, 150, 100, new Color(255, 0, 0));

    }

    @Override
    public void init() {
        addChild(
            new Button()
                .setX(100)
                .setY(100)
                .setWidth(100)
                .setHeight(100)


        );
    }

}
