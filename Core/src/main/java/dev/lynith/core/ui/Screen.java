package dev.lynith.core.ui;

import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;

public abstract class Screen<C extends Screen<C, S>, S extends AbstractComponentStyles<C, S>> extends ComponentWithChildren<C, S> {

    @Getter
    private final GuiScreens type;

    public Screen(GuiScreens type) {
        this.type = type;
    }

    public Screen() {
        this(GuiScreens.UNKNOWN);
    }

    @Override
    public void render(IRenderer ctx) {
        if (style().background() != null) {
            ctx.rect(0, 0, ctx.getWindowWidth(), ctx.getWindowHeight(), style().background());
        }

        super.render(ctx);
    }
}
