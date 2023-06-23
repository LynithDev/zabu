package dev.lynith.core.uiOld.impl;

import dev.lynith.core.uiOld.Component;
import dev.lynith.core.uiOld.features.ChildrenFeature;
import dev.lynith.core.uiOld.impl.layouts.ConstraintLayout;
import dev.lynith.core.uiOld.impl.layouts.AbstractLayout;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Panel extends Component implements ChildrenFeature {

    @Getter
    private final List<Component> children = new ArrayList<>();

    @Getter @Setter
    private Layout layout;

    public Panel(Layout layout) {
        this.layout = layout;
    }

    public Panel() {
        this(Layout.CONSTRAINT);
    }

    @Override
    public void render(IRenderer renderer) {
        getLayout().layout.render(renderer, children);
        super.render(renderer);
    }

    @Override
    public void init() {
        this.children.forEach(Component::init);
        setupHandlers(this);
    }

    @Override
    public void update() {
        getLayout().layout.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void addChild(Component child) {
        this.children.add(child);
    }

    @Override
    public void removeChild(int index) {
        this.children.remove(index);
    }

    public enum Layout {
        CONSTRAINT(ConstraintLayout.class),
        ABSOLUTE(null);

        @Getter
        private final AbstractLayout layout;

        Layout(Class<? extends AbstractLayout> layout) {
            try {
                this.layout = layout == null ? null : layout.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
