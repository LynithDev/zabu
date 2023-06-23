package dev.lynith.core.uiOld.impl;

import dev.lynith.core.Logger;
import dev.lynith.core.uiOld.Component;
import dev.lynith.core.uiOld.features.ChildrenFeature;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen extends Component implements ChildrenFeature {

    public Screen() {
        super();
        this.logger = new Logger(getClass().getSimpleName() + " Screen");

        addCallback(ResizeCallback.class, (width, height) -> this.children.forEach(Component::update));

        setupHandlers(this);
    }

    @Getter
    private final List<Component> children = new ArrayList<>();

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

    @FunctionalInterface
    public interface ResizeCallback extends CallbackInterface {
        void handle(int width, int height);
    }

}
