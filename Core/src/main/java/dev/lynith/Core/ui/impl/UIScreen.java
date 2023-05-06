package dev.lynith.Core.ui.impl;

import dev.lynith.Core.Logger;
import dev.lynith.Core.ui.Component;
import dev.lynith.Core.ui.features.ChildrenFeature;
import dev.lynith.Core.versions.IVersion;
import dev.lynith.Core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class UIScreen extends Component implements ChildrenFeature {

    public UIScreen() {
        super();
        this.logger = new Logger(getClass() + " UIScreen");

        setOnResize((width, height) -> this.children.forEach(Component::update));

        setupHandlers(this);
    }

    @Getter
    private List<Component> children = new ArrayList<>();

    @Override
    public void render(IRenderer renderer) {
        this.children.forEach(child -> child.render(renderer));
    }

    @Override
    public void init() {
        setParent(this);

        this.children.forEach(Component::init);
    }

    @Override
    public void update() {
        this.children.forEach(Component::update);
    }

    @Override
    public void addChild(Component child) {
        this.children.add(child);
    }

    @Override
    public void removeChild(int index) {
        this.children.remove(index);
    }

    @Getter @Setter
    private ResizeCallback onResize = (width, height) -> {};

    @FunctionalInterface
    public interface ResizeCallback {
        void handle(int width, int height);
    }

}
