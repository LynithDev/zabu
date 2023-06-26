package dev.lynith.core.hud;

import dev.lynith.core.ui.Screen;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class InGameHud extends Screen<InGameHud, ComponentStyles<InGameHud>> {

    @Override
    public void render(IRenderer ctx) {
        if (ctx.getCurrentScreenType() != GuiScreens.HUD_CONFIG_SCREEN) {
            super.render(ctx);
        }
    }

    @Override
    public void init() {
        add(HudManager.getInstance().getComponents());

        style(styles -> {
            styles.background(ZabuColor.TRANSPARENT);
        });

        super.init();
    }
}
