package dev.lynith.core.ui;

import dev.lynith.core.ui.callbacks.ComponentCallbacks;
import dev.lynith.core.ui.callbacks.impl.Destroyed;
import dev.lynith.core.ui.styles.AbstractComponentStyles;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ComponentWithChildren<C extends Component<C, S>, S extends AbstractComponentStyles<C, S>> extends Component<C, S> {

    @Getter @Accessors(fluent = false)
    private final CopyOnWriteArrayList<Component<?, ?>> children = new CopyOnWriteArrayList<>();

    @Override
    public void render(IRenderer ctx) {
        for (Component<?, ?> child : children) {
            child.render(ctx);
        }
        super.render(ctx);
    }

    @Override
    public void init() {
        listener(Destroyed.class, () -> getChildren().clear());

        for (Component<?, ?> child : children) {
            child.init();
        }

        super.init();
    }

    @Override
    public void update() {
        for (Component<?, ?> child : children) {
            child.update();
        }
        super.update();
    }

    @Override
    public void callCallbacks(Class<? extends ComponentCallbacks.CallbackInterface> callback, Object... args) {
        for (Component<?, ?> child : children) {
            child.callCallbacks(callback, args);
        }
        super.callCallbacks(callback, args);
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

    public <E extends Component<?,?>> C add(List<E> children) {
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

    public <E extends Component<?,?>> C remove(List<E> children) {
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
