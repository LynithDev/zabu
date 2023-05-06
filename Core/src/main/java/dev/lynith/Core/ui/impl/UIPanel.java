package dev.lynith.Core.ui.impl;

import dev.lynith.Core.ui.Component;
import dev.lynith.Core.ui.features.ChildrenFeature;
import dev.lynith.Core.versions.renderer.IRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A panel is a component that can contain other components. It is used to group components together, position and size them.
 * The default direction is column, meaning that components will be placed one after the other vertically.
 * @apiNote This is a very basic implementation of a panel. It does not support scrolling or any other advanced features.
 */
public class UIPanel extends Component implements ChildrenFeature {

    @Getter
    private List<Component> children = new ArrayList<>();

    /**
     * The spacing between each component in the panel.
     */
    @Getter @Setter
    private int spacing = 0;

    @Getter @Setter
    private Direction direction;

    public UIPanel(Direction direction) {
        this.direction = direction;
    }

    public UIPanel() {
        this(Direction.COLUMN);
    }

    public static UIPanel of(Component... components) {
        return of(Direction.COLUMN, components);
    }

    public static UIPanel of(Direction direction, Component... components) {
        UIPanel panel = new UIPanel(direction);
        panel.addChildren(components);
        return panel;
    }

    @Override
    public void render(IRenderer renderer) {
        int x = getX();
        int y = getY();

        for (Component child : children) {
            child.setX(x);
            child.setY(y);

            child.render(renderer);

            if (direction == Direction.COLUMN) {
                y += child.getHeight() + spacing;
            } else {
                x += child.getWidth() + spacing;
            }
        }
    }

    @Override
    public void init() {
        setupHandlers(this);
        this.children.forEach(Component::init);

        update();
    }

    @Override
    public void update() {
        switch (direction) {
            case COLUMN:
                updateColumn();
                break;
            case ROW:
                updateRow();
                break;
        }
    }

    private void updateColumn() {
        int width = 0;
        int height = 0;

        for (Component child : children) {
            width = Math.max(width, child.getWidth());
            height += child.getHeight() + spacing;
        }

        this.width = width;
        this.height = height;
    }

    private void updateRow() {
        int width = 0;
        int height = 0;

        for (Component child : children) {
            width += child.getWidth() + spacing;
            height = Math.max(height, child.getHeight());
        }

        this.width = width;
        this.height = height;
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
