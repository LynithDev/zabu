package dev.lynith.core.ui;

import dev.lynith.core.ui.callbacks.ComponentCallbacks;
import dev.lynith.core.ui.styles.ComponentStyles;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ComponentWithChildren<C extends Component<C, S>, S extends ComponentStyles<C, S>> extends Component<C, S> {

    @Getter
    private final List<Component<?, ?>> children = new ArrayList<>();

    @Override
    public void render(IRenderer ctx) {
        for (Component<?, ?> child : children) {
            child.render(ctx);
        }
        super.render(ctx);
    }

    @Override
    public <CB extends ComponentCallbacks.CallbackInterface> C listener(Class<CB> callback, CB callbackInstance) {
        for (Component<?, ?> child : children) {
            child.listener(callback, callbackInstance);
        }
        return super.listener(callback, callbackInstance);
    }

    @Override
    public <CB extends ComponentCallbacks.CallbackInterface> C listener(CB callbackInstance) {
        for (Component<?, ?> child : children) {
            child.listener(callbackInstance);
        }
        return super.listener(callbackInstance);
    }

    public C add(Component<?, ?> child) {
        children.add(child);
        return (C) this;
    }

    public C remove(Component<?, ?> child) {
        children.remove(child);
        return (C) this;
    }

    public C remove(int index) {
        children.remove(index);
        return (C) this;
    }

    public C clear() {
        children.clear();
        return (C) this;
    }

    public C add(Component<?, ?>... children) {
        for (Component<?, ?> child : children) {
            add(child);
        }
        return (C) this;
    }

    public C add(List<Component<?, ?>> children) {
        for (Component<?, ?> child : children) {
            add(child);
        }
        return (C) this;
    }

    public C remove(Component<?, ?>... children) {
        for (Component<?, ?> child : children) {
            remove(child);
        }
        return (C) this;
    }

    public C remove(List<Component<?, ?>> children) {
        for (Component<?, ?> child : children) {
            remove(child);
        }
        return (C) this;
    }

    public C remove(int... indexes) {
        for (int index : indexes) {
            remove(index);
        }
        return (C) this;
    }

}
