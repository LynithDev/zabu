package dev.lynith.core.hud;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.impl.MouseDrag;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;

public class PvpHudComponent extends Component<PvpHudComponent, PvpHudComponent.PvpHudComponentStyle> {

    public PvpHudComponent() {
        setStyles(new PvpHudComponentStyle(this));
    }

    @Override
    public void init() {
        size(100, 40);
        super.init();
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
