package dev.lynith.core.ui;

import dev.lynith.core.utils.ExtendedHashMap;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ComponentProperties extends ExtendedHashMap<Object> {

    private int leftConstraint = 0;
    private int rightConstraint = 0;
    private int topConstraint = 0;
    private int bottomConstraint = 0;

}
