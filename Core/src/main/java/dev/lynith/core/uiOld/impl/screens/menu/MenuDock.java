package dev.lynith.core.uiOld.impl.screens.menu;

import dev.lynith.core.uiOld.impl.PanelOld;
import dev.lynith.core.uiOld.impl.components.Label;
import dev.lynith.core.utils.Formatting;
import dev.lynith.core.versions.renderer.IRenderer;

public class MenuDock extends PanelOld {

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(x, y, width, height, from(231, 231, 240));
        super.render(renderer);
    }

    @Override
    public void init() {
        Label label = new Label("Hello, " + Formatting.BOLD.of(bridge.getProfile().getUsername()));
        addChild(label);
        label.setBounds(x + 5, y + 5);

        super.init();
    }
}
