package dev.lynith.core.hud;

import dev.lynith.core.config.ConfigManager;
import dev.lynith.core.config.ConfigOption;
import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.impl.MouseDrag;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

public class PvpHudComponent extends Component<PvpHudComponent, PvpHudComponent.PvpHudComponentStyle> {

    @Accessors(fluent = false)
    @Getter @Setter
    @ConfigOption
    private int[] bounds = new int[4];

    public PvpHudComponent() {
        setStyles(new PvpHudComponentStyle(this));
    }

    @Override
    public void init() {
        if (bounds != null && bounds.length == 4) {
            position(bounds[0], bounds[1]);
            size(bounds[2], bounds[3]);
        } else {
            position(x(), y());
            size(width(), height());
        }

        if (width() == 0) width(80);
        if (height() == 0) height(20);

        listener(MouseDrag.class, (mouseX, mouseY) -> {
            bounds = new int[4];
            bounds[0] = x();
            bounds[1] = y();
            bounds[2] = width();
            bounds[3] = height();
        });

        super.init();
    }

    protected void renderAverageLook(IRenderer renderer, String text) {
        int textX = x() + (width() / 2) - (renderer.getTextWidth(text) / 2);
        int textY = y() + (height() / 2) - (renderer.getTextHeight() / 2);

        renderer.rect(x(), y(), width(), height(), ZabuColor.from(10, 10, 10, 200));
        renderer.text(text, textX, textY, ZabuColor.WHITE);
    }

    public static class PvpHudComponentStyle extends AbstractComponentStyles<PvpHudComponent, PvpHudComponentStyle> {

        public PvpHudComponentStyle(PvpHudComponent component) {
            super(component);
        }

        @Override
        public boolean draggable() {
            return true;
        }
    }

}
