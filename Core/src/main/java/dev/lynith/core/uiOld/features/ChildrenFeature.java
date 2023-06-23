package dev.lynith.core.uiOld.features;

import dev.lynith.core.uiOld.Component;

import java.util.List;

import static dev.lynith.core.uiOld.Component.isIntersecting;

public interface ChildrenFeature {

    // Required
    List<Component> getChildren();

    default void addChild(Component child) {
        getChildren().add(child);
    }

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
        self.addCallback(Component.DestroyCallback.class, () -> {
            getChildren().forEach(child -> child.callCallbacks(Component.DestroyCallback.class));
        });

        // TODO: Make this only apply if the component is focused
        self.addCallback(Component.KeyTypedCallback.class, (typedChar, keyCode) -> {
            getChildren().forEach(child -> child.callCallbacks(Component.KeyTypedCallback.class, typedChar, keyCode));
        });

        self.addCallback(Component.ClickCallback.class, (mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.callCallbacks(Component.ClickCallback.class, mouseX, mouseY);
                }
            });
        });

        self.addCallback(Component.ReleaseCallback.class, (mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.callCallbacks(Component.ReleaseCallback.class, mouseX, mouseY);
                }
            });
        });

        self.addCallback(Component.PressCallback.class, (mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.callCallbacks(Component.PressCallback.class, mouseX, mouseY);
                }
            });
        });

        self.addCallback(Component.DragCallback.class, (mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.callCallbacks(Component.DragCallback.class, mouseX, mouseY);
                }
            });
        });

        self.addCallback(Component.EnterCallback.class, (mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                if (isIntersecting(child, mouseX, mouseY)) {
                    child.setMouseInside(true);
                    child.callCallbacks(Component.EnterCallback.class, mouseX, mouseY);
                } else {
                    child.callCallbacks(Component.LeaveCallback.class, mouseX, mouseY);
                }
            });
        });

        self.addCallback(Component.LeaveCallback.class, (mouseX, mouseY) -> {
            getChildren().forEach(child -> {
                child.setMouseInside(false);
                child.callCallbacks(Component.LeaveCallback.class, mouseX, mouseY);
            });
        });
    }

    default void removeChild(int index) {
        getChildren().remove(index);
    }

}
