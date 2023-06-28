package dev.lynith.core.hud.impl;

import dev.lynith.core.hud.PvpHudComponent;
import dev.lynith.core.versions.renderer.IRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockComponent extends PvpHudComponent {

    private final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

    @Override
    public void render(IRenderer ctx) {
        String formatted = formatter.format(new Date());
        renderAverageLook(ctx, formatted);

        super.render(ctx);
    }
}
