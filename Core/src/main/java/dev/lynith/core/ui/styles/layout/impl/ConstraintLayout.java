package dev.lynith.core.ui.styles.layout.impl;

import dev.lynith.core.ui.Component;
import dev.lynith.core.ui.styles.layout.AbstractLayout;
import dev.lynith.core.versions.renderer.IRenderer;

public class ConstraintLayout extends AbstractLayout {

    @Override
    public void updateChild(IRenderer ctx, Component<?, ?> child, int index) {
        int childX = holder.x() + child.properties().leftConstraint();
        int childY = holder.y() + child.properties().topConstraint();
        int childWidth = holder.x() + child.properties().rightConstraint() - childX;
        int childHeight = holder.y() + child.properties().bottomConstraint() - childY;

        child.position(childX, childY);
        child.size(childWidth, childHeight);
    }

}
