package dev.lynith.core.uiOld.impl.components;

import dev.lynith.core.uiOld.Component;
import dev.lynith.core.uiOld.features.TextFeature;
import dev.lynith.core.utils.ZabuColor;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;

public class Button extends Component implements TextFeature {

    @Getter
    private String text;

    public Button(String text) {
        this.text = text;
    }

    private ZabuColor color = ZabuColor.from(50);

    public Button() {
        this("Placeholder");
    }

    @Override
    public void render(IRenderer renderer) {
        renderer.rect(this.x, this.y, this.width, this.height, color);
        renderer.text(this.text,
                this.x + (this.width / 2 - renderer.getTextWidth(this.text) / 2),
                this.y + (this.height / 2 - renderer.getTextHeight() / 2) + 1,
                ZabuColor.from(255));
    }

    @Override
    public void init() {
        this.width = bridge.getRenderer().getTextWidth(this.text) + 10;
        this.height = bridge.getRenderer().getTextHeight() + 10;

        addCallback(EnterCallback.class, (mouseX, mouseY) -> {
            this.color = ZabuColor.from(70);
        });

        addCallback(LeaveCallback.class, (mouseX, mouseY) -> {
            this.color = ZabuColor.from(50);
        });
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
