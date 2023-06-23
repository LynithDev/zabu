package dev.lynith.core.uiOld.impl.screens.menu;

import dev.lynith.core.uiOld.impl.Screen;
import dev.lynith.core.versions.renderer.IRenderer;

public class ModMenu extends Screen {

    @Override
    public void render(IRenderer renderer) {
        super.render(renderer);
    }

    @Override
    public void init() {
        MenuDock dock = new MenuDock();
        addChild(dock);

        addCallback(ResizeCallback.class, (w, h) -> {
            dock.setBounds((w / 2) - (dock.getWidth() / 2), h - 10);
        });

        super.init();
    }

}
