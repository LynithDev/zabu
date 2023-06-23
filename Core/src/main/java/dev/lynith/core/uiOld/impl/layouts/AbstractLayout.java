package dev.lynith.core.uiOld.impl.layouts;

import dev.lynith.core.uiOld.Component;
import dev.lynith.core.versions.renderer.IRenderer;

import java.util.List;

public abstract class AbstractLayout {

    protected int x, y, width, height;

    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(IRenderer renderer, List<Component> children);

}
