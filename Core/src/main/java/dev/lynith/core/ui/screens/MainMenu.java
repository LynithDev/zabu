package dev.lynith.core.ui.screens;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.Screen;
import dev.lynith.core.ui.components.impl.Button;
import dev.lynith.core.ui.styles.impl.Color;
import dev.lynith.core.utils.nvg.FontHelper;

public class MainMenu extends Screen {

    @Override
    public void render(IRenderer renderer, int mouseX, int mouseY, float delta) {

        rectangle(0, 0, renderer.getWindowWidth(), renderer.getWindowHeight(), new Color(0, 255));
        circle(150, 150, 100, new Color(255, 0, 0));

        text("Test", getX(), getY() + 300, 48, new Color(255), FontHelper.get("Roboto-Regular"));

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
