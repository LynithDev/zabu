package dev.lynith.core.ui.components.features;

import dev.lynith.core.ui.components.Component;

import java.util.List;

public interface Children {

    List<Component<?>> getChildren();

    default void addChild(Component<?> child) {
        getChildren().add(child);
    }

    default void addChildren(Component<?>... children) {
        for (Component<?> child : children) {
            addChild(child);
        }
    }

    default void addChildren(List<Component<?>> children) {
        getChildren().addAll(children);
    }

    default void removeChild(Component<?> child) {
        getChildren().remove(child);
    }

    default void setChildren(List<Component<?>> children) {
        getChildren().clear();
        getChildren().addAll(children);
    }

    default void clearChildren() {
        getChildren().clear();
    }

}