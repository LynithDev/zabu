package dev.lynith.core.hud.impl;

import dev.lynith.core.hud.PvpHudComponent;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class FPSComponent extends PvpHudComponent {

    @Override
    public void render(IRenderer ctx) {
        renderAverageLook(ctx, "FPS: " + bridge().getGame().getFps());

        super.render(ctx);
    }

}
