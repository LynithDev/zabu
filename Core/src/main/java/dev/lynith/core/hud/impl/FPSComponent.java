package dev.lynith.core.hud.impl;

import dev.lynith.core.hud.PvpHudComponent;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class FPSComponent extends PvpHudComponent {

    @Override
    public void render(IRenderer ctx) {
        ctx.rect(x(), y(), width(), height(), ZabuColor.from(0));
        ctx.text("FPS: " + bridge().getGame().getFps(), x(), y());

        super.render(ctx);
    }

}
