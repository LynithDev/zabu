package dev.lynith.core.ui.styles.layout;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.Component;
import dev.lynith.core.versions.IVersion;
import dev.lynith.core.versions.renderer.IRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractLayout {

    private final IVersion bridge = ClientStartup.getInstance().getBridge();
    private final List<Component<?,?>> children = new CopyOnWriteArrayList<>();

    protected boolean vertical = false;
    protected Component<?,?> holder;
    protected List<Component<?,?>> getChildren() {
        return children;
    }

    public void render(IRenderer ctx) {
        for (Component<?,?> child : children) {
            child.render(ctx);
        }
    }

    public abstract void updateChild(IRenderer ctx, Component<?,?> child, int index);

    public void update(IRenderer ctx) {
        for (int i = 0; i < children.size(); i++) {
            updateChild(ctx, children.get(i), i);
        }
    }

    public void init(Component<?,?> holder, boolean vertical, List<Component<?,?>> children) {
        this.holder = holder;
        this.vertical = vertical;
        this.children.addAll(children);
    }

}
