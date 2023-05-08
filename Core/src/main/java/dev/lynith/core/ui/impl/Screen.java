package dev.lynith.core.ui.impl;

import dev.lynith.core.Logger;
import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.features.ChildrenFeature;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen extends Component implements ChildrenFeature {

    public Screen() {
        super();
        this.logger = new Logger(getClass().getSimpleName() + " UIScreen");

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
