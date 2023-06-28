package dev.lynith.core.ui.components;

import dev.lynith.core.ui.Component;
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
                new ButtonHoverStyles(this));
    }

    public Button() {
        this("Click Me!");
    }

    @Override
    public void render(IRenderer ctx) {
        ctx.rect(x(), y(), width(), height(), style().background());
        ctx.text(text, x(), y(), style().textColor());
        super.render(ctx);
    }

    @Getter @Setter
    public static class ButtonStyles extends AbstractComponentStyles<Button, ButtonStyles> {

        public ButtonStyles(Button component) {
            super(component);
        }

        private ZabuColor textColor = ZabuColor.from(255);
    }

    @Getter @Setter
    public static class ButtonHoverStyles extends ButtonStyles {

        public ButtonHoverStyles(Button component) {
            super(component);
        }

        @Override
        public ZabuColor textColor() {
            return ZabuColor.from(255, 255, 0);
        }
    }

}
