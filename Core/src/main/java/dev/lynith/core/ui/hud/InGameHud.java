package dev.lynith.core.ui.hud;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.impl.Screen;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class InGameHud extends Screen {

    @Override
    public void render(IRenderer renderer) {
        if (ClientStartup.getInstance().getBridge().getRenderer().getCurrentScreen() != GuiScreens.HUD_CONFIG_SCREEN) {
            super.render(renderer);
        }
    }

    @Override
    public void init() {
        addChildren(HudManager.getInstance().getComponents());

        super.init();
    }
}
