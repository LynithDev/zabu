package dev.lynith.core.ui.hud;

import dev.lynith.core.ui.Component;
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
        Component component = new Component() {
            @Override
            public void render(IRenderer renderer) {
                renderer.rect(getX(), getY(), getWidth(), getHeight(), ZabuColor.from(0));
            }

            @Override
            public void init() {
                setDraggable(true);
                setWidth(100);
                setHeight(100);
                setX(100);
                setY(100);
            }
        };

        component.addCallback(ReleaseCallback.class, (mouseX, mouseY) -> {

        });

        addChild(component);

        super.init();
    }
}
