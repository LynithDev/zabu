package dev.lynith.Core.ui.impl.components;

import dev.lynith.Core.ui.Component;
import dev.lynith.Core.ui.features.TextFeature;
import dev.lynith.Core.utils.ZabuColor;
import dev.lynith.Core.versions.IVersion;
import dev.lynith.Core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

public class Button extends Component implements TextFeature {

    @Getter
    private String text = "Click me!";

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(this.x, this.y, this.width, this.height, ZabuColor.from(50));
        renderer.text(this.text,
                this.width / 2 - renderer.getTextWidth(this.text) / 2,
                this.height / 2 - renderer.getTextHeight() / 2,
                ZabuColor.from(255));
    }

    @Override
    public void init() {
        this.width = bridge.getRenderer().getTextWidth(this.text) + 10;
        this.height = bridge.getRenderer().getTextHeight() + 10;
    }

    @Override
    public void setText(String text, boolean updateSize) {
        this.text = text;
        if (updateSize) {
            this.width = this.bridge.getRenderer().getTextWidth(this.text) + 10;
            this.height = this.bridge.getRenderer().getTextHeight() + 10;
        }
    }
}
