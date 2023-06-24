package dev.lynith.core.ui.components;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.impl.MouseClick;
import dev.lynith.core.ui.callbacks.impl.MouseEnter;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class Button extends Component<Button, Button.ButtonStyles> {

    @Getter @Setter
    private String text;

    public Button(String text) {
        super();
        this.text = text;

        setStyles(new ButtonStyles(this),
                new ButtonHoverStyles(this),
                new ButtonHoverStyles(this)); // For now only has 3 states, normal, hover, and click
    }

    public Button() {
        this("Click Me!");
    }

    @Override
    public void render(IRenderer ctx) {
        ctx.rect(x(), y(), width(), height(), getStyle().getBackground());
        ctx.text(text, x(), y(), getStyle().getTextColor());
        super.render(ctx);
    }

    @Getter @Setter
    public static class ButtonStyles extends AbstractComponentStyles<Button, ButtonStyles> { // Per component styles
        // Meaning these styles won't be accessible on non Button components (Unless it extends Button of course)

        public ButtonStyles(Button component) {
            super(component);
        }

        private ZabuColor textColor = ZabuColor.from(255);
    }

    public static class ButtonHoverStyles extends ButtonStyles {

        public ButtonHoverStyles(Button component) {
            super(component);
        }

        @Override
        public ZabuColor getTextColor() { // Overrides the base styles
            return ZabuColor.from(255, 255, 0); // yellow
        }
    }

}
