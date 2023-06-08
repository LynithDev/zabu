package dev.lynith.core.ui.hud;

import dev.lynith.core.ui.DraggableComponent;
import dev.lynith.core.ui.impl.Screen;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class HUD extends Screen {

    @Override
    public void render(IRenderer renderer) {
        super.render(renderer);
    }

    @Override
    public void init() {
        addChild(new DraggableComponent() {
            @Override
            public void render(IRenderer renderer) {
                renderer.rect(getX(), getY(), getWidth(), getHeight(), ZabuColor.from(0));
            }

            @Override
            public void init() {
                setWidth(100);
                setHeight(100);
                setX(100);
                setY(100);
            }
        });

        super.init();
    }
}
