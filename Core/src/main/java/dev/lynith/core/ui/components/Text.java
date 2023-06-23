package dev.lynith.core.ui.components;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class Text extends Component<Text, Text.TextStyles> {

    @Getter @Setter
    private String text;

    public Text(String text) {
        this.text = text;
        setStyles(new TextStyles(this));
    }

    public Text() {
        this("");
    }

    @Override
    public void render(IRenderer ctx) {


        super.render(ctx);
    }

    public static class TextStyles extends ComponentStyles<Text, TextStyles> {

        public TextStyles(Text component) {
            super(component);
        }

        public ZabuColor textColor = ZabuColor.from(255);
        public boolean textShadow = false;
        public int textSize = 16;

    }
}
