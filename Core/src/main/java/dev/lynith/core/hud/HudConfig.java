package dev.lynith.core.hud;

import dev.lynith.core.ui.Screen;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class HudConfig extends Screen<HudConfig, ComponentStyles<HudConfig>> {

    boolean firstRender = false;

    @Override
    public void render(IRenderer ctx) {
        if (!firstRender) {
            HudManager.getInstance().getComponents().forEach(component -> {
                System.out.println(component.x() + " " + component.y() + " " + component.width() + " " + component.height());
            });
            firstRender = true;
        }

        super.render(ctx);
    }

    @Override
    public void init() {
        add(HudManager.getInstance().getComponents());
        HudManager.getInstance().getComponents().forEach(component -> {
            System.out.println(component.x() + " " + component.y() + " " + component.width() + " " + component.height());
        });

        style(styles -> {
            styles.background(ZabuColor.TRANSPARENT);
        });

        super.init();
    }
}
