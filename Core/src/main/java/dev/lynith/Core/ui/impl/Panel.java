package dev.lynith.Core.ui.impl;

import dev.lynith.Core.ui.Component;
import dev.lynith.Core.ui.features.ChildrenFeature;
import dev.lynith.Core.utils.ZabuColor;
import dev.lynith.Core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
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

    private int originalWidth, originalHeight = 0;

    public ZabuColor outline;

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
        if (outline != null) renderer.rectOutline(getX(), getY(), getWidth(), getHeight(), 1, outline);
    }

    @Override
    public void init() {
        if (this.originalWidth == 0) this.originalWidth = getWidth();
        if (this.originalHeight == 0) this.originalHeight = getHeight();

        this.children.forEach(Component::init);
        setupHandlers(this);

        update();
    }

    @Override
    public void update() {
        int childX = getX() + spacing;
        int childY = getY() + spacing;

        if (!fillWidth) setWidth(originalWidth);
        if (!fillHeight) setHeight(originalHeight);

        // TODO: Fix child width size calculation
        int childWidth = (getWidth() - (spacing * (2 + 1))) / (getChildren().size());
        int childHeight = (getHeight() - (spacing * 2)) / (getChildren().size() + (direction == Direction.COLUMN && spacing > 0 ? 1 : 0));

        for (Component child : getChildren()) {
            child.init();
            if (equalSizeChildren) {
                if (getWidth() > 0) {
                    child.setFillWidth(true);
                    child.setWidth(direction == Direction.COLUMN ? getWidth() - (spacing * 2) : childWidth);
                }

                if (getHeight() > 0 ) {
                    child.setFillHeight(true);
                    child.setHeight(direction == Direction.COLUMN ? childHeight : getHeight() - (spacing * 2));
                }
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
