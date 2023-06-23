package dev.lynith.core.ui.components;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.callbacks.impl.MouseClick;
import dev.lynith.core.ui.callbacks.impl.MouseDrag;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class Button extends Component<Button, Button.ButtonStyles> {

    @Getter @Setter
    private String text;

    public Button(String text) {
        this.text = text;
        setStyles(new ButtonStyles(this));
    }

    public Button() {
        this("Click Me!");
    }

    @Override
    public void render(IRenderer ctx) {
        super.render(ctx);
    }

    @Override
    public void init() {
        listener(MouseClick.class, (mouseX, mouseY) -> {
            System.out.println("Clicked!");
        });

        super.init();
    }

    public static class ButtonStyles extends ComponentStyles<Button, ButtonStyles> {

        public ButtonStyles(Button component) {
            super(component);
        }

    }

}
