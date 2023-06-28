package dev.lynith.core.ui.components;

import dev.lynith.core.ui.ComponentWithChildren;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.ui.styles.layout.AbstractLayout;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class Panel extends ComponentWithChildren<Panel, Panel.PanelStyles> {

    public Panel() {
        setStyles(new PanelStyles(this));
    }

    @Override
    public void render(IRenderer ctx) {
        style().layout().render(ctx);

        super.render(ctx);
    }

    @Override
    public void init() {
        style().layout().init(this.getChildren());

        super.init();
    }

    @Getter @Setter
    public static class PanelStyles extends AbstractComponentStyles<Panel, PanelStyles> {

        public PanelStyles(Panel component) {
            super(component);
        }

        private AbstractLayout layout;

    }

}
