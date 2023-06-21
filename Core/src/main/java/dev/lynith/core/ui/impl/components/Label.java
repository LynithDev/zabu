package dev.lynith.core.ui.impl.components;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.features.TextFeature;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class Label extends Component implements TextFeature {

    @Getter
    private String text;

    @Getter @Setter
    private boolean shadow;

    @Getter @Setter
    private ZabuColor color;

    public Label() {
        this("");
    }

    public Label(Object text) {
        this.text = text.toString();
        this.color = ZabuColor.from(0);
        this.shadow = false;
    }

    @Override
    public void render(IRenderer renderer) {
        renderer.text(this.text, getX(), getY(), this.color, this.shadow);
    }

    @Override
    public void init() {
        width = bridge.getRenderer().getTextWidth(this.text);
        height = bridge.getRenderer().getTextHeight();
    }

    @Override
    public void setText(String text, boolean updateSize) {
        this.text = text;
        if (updateSize) {
            width = bridge.getRenderer().getTextWidth(this.text);
            height = bridge.getRenderer().getTextHeight();
        }
    }

    @Override
    public void setText(String text) {
        setText(text, true);
    }
}
