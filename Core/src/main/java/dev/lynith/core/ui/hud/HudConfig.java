package dev.lynith.core.ui.hud;

import dev.lynith.core.ui.impl.Screen;
import dev.lynith.core.versions.renderer.IRenderer;

public class HudConfig extends Screen {

    @Override
    public void render(IRenderer renderer) {
        super.render(renderer);
    }

    @Override
    public void init() {
        addChildren(HudManager.getInstance().getComponents());

        super.init();
    }
}
