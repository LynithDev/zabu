package dev.lynith.core.uiOld.hud.impl;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.config.ConfigOption;
import dev.lynith.core.uiOld.Component;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class FPSComponent extends Component {

    @Getter @Setter
    @ConfigOption(nameSerialized = "hud.fps.x")
    protected int x;

    @Getter @Setter
    @ConfigOption(nameSerialized = "hud.fps.y")
    protected int y;

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(getX(), getY(), getWidth(), getHeight(), ZabuColor.from(0, 0, 0, 200));
        renderer.textCentered("FPS: " + ClientStartup.getInstance().getBridge().getGame().getFps(), getX() + getWidth() / 2, getY() + getHeight() / 2, ZabuColor.from(255, 255, 255));

        super.render(renderer);
    }

    @Override
    public void init() {
        setDraggable(true);
        setSize(100, 20);

        super.init();
    }
}
