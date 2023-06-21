package dev.lynith.core.ui.impl.screens.menu;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.features.ChildrenFeature;
import dev.lynith.core.ui.impl.Panel;
import dev.lynith.core.ui.impl.components.Label;
import dev.lynith.core.utils.Formatting;
import dev.lynith.core.versions.renderer.IRenderer;

public class MenuDock extends Panel {

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
