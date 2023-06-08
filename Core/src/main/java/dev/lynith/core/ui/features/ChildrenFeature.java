package dev.lynith.core.ui.features;

import dev.lynith.core.ui.Component;

import java.util.List;

import static dev.lynith.core.ui.Component.isIntersecting;

public interface ChildrenFeature {

    void addChild(Component child);

    default void addChildren(Component... children) {
        for (Component child : children) {
            addChild(child);
        }
    }

    default void addChildren(List<Component> children) {
        for (Component child : children) {
            addChild(child);
        }
    }

    default void clearChildren() {
        getChildren().clear();
    }


    /**
     * Handlers should be setup in the init method of the component.
     * @param self The component to setup handlers for
     */
    default void setupHandlers(Component self) {
        self.setOnDestroy(() -> getChildren().forEach(child -> child.getOnDestroy().handle()));

        // TODO: Make this only apply if the component is focused
        self.setOnKeyTyped((typedChar, keyCode) -> getChildren().forEach(child -> child.getOnKeyTyped().handle(typedChar, keyCode)));

        self.setOnClick((mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.getOnClick().handle(mouseX, mouseY);
                }
            });
        });

        self.setOnRelease((mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.getOnRelease().handle(mouseX, mouseY);
                }
            });
        });

        self.setOnPress((mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.getOnPress().handle(mouseX, mouseY);
                }
            });
        });

        self.setOnDrag((mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.getOnDrag().handle(mouseX, mouseY);
                }
            });
        });

        self.setOnEnter((mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.setMouseInside(true);
                    child.getOnEnter().handle(mouseX, mouseY);
                } else {
                    child.setMouseInside(false);
                    child.getOnLeave().handle(mouseX, mouseY);
                }
            });
        });

        self.setOnLeave((mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                child.setMouseInside(false);
                child.getOnLeave().handle(mouseX, mouseY);
            });
        });
    }

    void removeChild(int index);

    List<Component> getChildren();

}
