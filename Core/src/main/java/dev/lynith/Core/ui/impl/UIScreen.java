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

        setOnDestroy(() -> this.children.forEach(child -> child.getOnDestroy().handle()));
        setOnResize((screen, width, height) -> this.children.forEach(Component::update));

        // TODO: Make this only apply if the component is focused
        setOnKeyTyped((typedChar, keyCode) -> this.children.forEach(child -> child.getOnKeyTyped().handle(typedChar, keyCode)));

        setOnClick((mouseX, mouseY) -> {
            this.children.forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.getOnClick().handle(mouseX, mouseY);
                }
            });
        });

        setOnDrag((mouseX, mouseY) -> {
            this.children.forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.getOnDrag().handle(mouseX, mouseY);
                }
            });
        });

        setOnEnter((mouseX, mouseY) -> {
            this.children.forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.setMouseInside(true);
                    child.getOnEnter().handle(mouseX, mouseY);
                } else if (child.isMouseInside()) {
                    child.setMouseInside(false);
                    child.getOnLeave().handle(mouseX, mouseY);
                }
            });
        });

        this.children.forEach(Component::init);
    }

    private boolean isIntersecting(Component component, int pointX, int pointY) {
        return pointX >= component.getX() &&                          // Left border
                pointX <= component.getX() + component.getWidth() &&  // Right border
                pointY >= component.getY() &&                         // Top border
                pointY <= component.getY() + component.getHeight();   // Bottom
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
    public void removeChild(Component child) {
        this.children.remove(child);
    }

    @Override
    public void clearChildren() {
        this.children.clear();
    }

    @Getter @Setter
    private ResizeCallback onResize = (screen, width, height) -> {};

    @FunctionalInterface
    public interface ResizeCallback {
        void handle(UIScreen screen, int width, int height);
    }

}
