package dev.lynith.Core.ui.impl;

import dev.lynith.Core.ui.impl.components.Button;
import dev.lynith.Core.utils.ZabuColor;
import dev.lynith.Core.versions.renderer.IRenderer;

public class ZabuMainMenu extends UIScreen {

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(0, 0, renderer.getDisplayWidth(), renderer.getDisplayHeight(), ZabuColor.from(0));
        super.render(renderer);
    }

    @Override
    public void init() {
        Button button = new Button();

        button.setText("My custom button!");

        button.setOnClick((mouseX, mouseY) -> {
            System.out.println("Clicked button");
        });

        button.setOnDrag((mouseX, mouseY) -> {
            System.out.println("Dragging button for some reason?");
        });

        button.setOnEnter((mouseX, mouseY) -> {
            button.setText("Hovering over button!");
        });

        button.setOnLeave((mouseX, mouseY) -> {
            button.setText("No longer hovering");
        });

        addChild(button);

        super.init();
    }
}
