package dev.lynith.core.hud;

import dev.lynith.core.config.ConfigManager;
import dev.lynith.core.ui.Screen;
import dev.lynith.core.ui.callbacks.impl.Destroyed;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class HudConfig extends Screen<HudConfig, ComponentStyles<HudConfig>> {

    boolean firstRender = false;

    public HudConfig() {
        super(GuiScreens.HUD_CONFIG_SCREEN);
    }

    @Override
    public void render(IRenderer ctx) {
        super.render(ctx);
    }

    @Override
    public void init() {
        add(HudManager.getInstance().getComponents());

        style(styles -> {
            styles.background(ZabuColor.TRANSPARENT);
        });

        listener(Destroyed.class, () -> {
            ConfigManager.getInstance().save();
        });

        super.init();
    }
}
