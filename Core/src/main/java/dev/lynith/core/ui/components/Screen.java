package dev.lynith.core.ui.components;

import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.core.ui.components.features.Children;
import dev.lynith.core.ui.styles.ComponentStyles;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Screen extends Component<Screen, ComponentStyles<?, ?>> implements Children {

    private final List<Component<?, ?>> children = new ArrayList<>();

    @Override
    protected void postRender(IRenderer ctx, int mouseX, int mouseY, float delta) {
        for (Component<?, ?> child : children) {
            child.render(ctx, mouseX, mouseY, delta);
        }
        super.postRender(ctx, mouseX, mouseY, delta);
    }

    @Override
    protected void postInit() {
        super.postInit();
        for (Component<?, ?> child : children) {
            child.init();
        }
    }

    @Override
    public <T extends ComponentCallback> void on(Class<T> event, T callback, boolean once) {
        super.on(event, callback, once);
        for (Component<?, ?> child : children) {
            child.on(event, callback, once);
        }
    }

    @Override
    public ComponentStyles<?, ?> getStyles() {
        return null;
    }
}
