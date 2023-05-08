package dev.lynith.core.ui.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.features.ChildrenFeature;
import dev.lynith.core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A panel is a component that can contain other components. It is used to group components together, position and size them.
 * The default direction is column, meaning that components will be placed one after the other vertically.
 * @apiNote This is a very basic implementation of a panel. It does not support scrolling or any other advanced features.
 */
public class Panel extends Component implements ChildrenFeature {

    @Getter
    private List<Component> children = new ArrayList<>();

    /**
     * The spacing between each component in the panel.
     */
    @Getter @Setter
    private int spacing = 0;

    @Getter @Setter
    private Direction direction;

    @Getter @Setter
    private boolean equalSizeChildren;

    @Setter
    private int initialWidth, initialHeight = 0;

    public Panel(Direction direction) {
        this.direction = direction;
    }

    public Panel() {
        this(Direction.COLUMN);
    }

    public static Panel of(Component... components) {
        return of(Direction.COLUMN, components);
    }

    public static Panel of(Direction direction, Component... components) {
        Panel panel = new Panel(direction);
        panel.addChildren(components);
        return panel;
    }

    @Override
    public void render(IRenderer renderer) {
        getChildren().forEach((child) -> child.render(renderer));
    }

    @Override
    public void init() {
        this.children.forEach(Component::init);
        setupHandlers(this);

        update();
    }

    @Override
    public void update() {
        int childX = getX() + spacing;
        int childY = getY() + spacing;

        if (!fillWidth && initialWidth > 0) setWidth(initialWidth);
        if (!fillHeight && initialHeight > 0) setHeight(initialHeight);

        int childHeight = (getHeight() - (spacing * (getChildren().size() + 1))) / getChildren().size();
        int childWidth = (getWidth() - (spacing * (getChildren().size() + 1))) / getChildren().size();

        for (Component child : getChildren()) {
            if (equalSizeChildren) {
                if (getWidth() <= 0 || childWidth <= 0) {
                    setWidth(child.getWidth() + spacing * 2);
                    childWidth = (getWidth() - (spacing * (getChildren().size() + 1))) / getChildren().size();
                }
                child.setFillWidth(true);
                child.setWidth(direction == Direction.COLUMN ? getWidth() - (spacing * 2) : childWidth);

                if (getHeight() <= 0 || childHeight <= 0) {
                    setHeight(child.getHeight() + spacing * 2);
                    childHeight = (getHeight() - (spacing * (getChildren().size() + 1))) / getChildren().size();
                }
                child.setFillHeight(true);
                child.setHeight(direction == Direction.COLUMN ? childHeight : getHeight() - (spacing * 2));

            } else {
                if (direction == Direction.COLUMN) {
                    child.setFillWidth(true);
                    child.setWidth(getWidth() - (spacing * 2));
                } else {
                    child.setFillHeight(true);
                    child.setHeight(getHeight() - (spacing * 2));
                }
            }

            child.setX(childX);
            child.setY(childY);

            if (direction == Direction.COLUMN) {
                childY += child.getHeight() + spacing;
                if (!fillHeight) setHeight(childY - getY());
            } else {
                childX += child.getWidth() + spacing;
                if (!fillWidth) setWidth(childX - getX());
            }
            child.update();
        }

    }

    @Override
    public void addChild(Component child) {
        this.children.add(child);
        update();
    }

    @Override
    public void removeChild(int index) {
        this.children.remove(index);
        update();
    }

    public enum Direction {
        COLUMN,
        ROW
    }

}
