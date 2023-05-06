package dev.lynith.Core.ui.features;

import dev.lynith.Core.ui.Component;

import java.util.ArrayList;
import java.util.List;

public interface ChildrenFeature {

    void addChild(Component child);
    void removeChild(Component child);

    List<Component> getChildren();

}
