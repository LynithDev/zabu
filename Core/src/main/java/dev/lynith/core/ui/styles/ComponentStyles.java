package dev.lynith.core.ui.styles;

import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.ZabuColor;

public class ComponentStyles<C extends Component<C, ComponentStyles<C>>> extends AbstractComponentStyles<C, ComponentStyles<C>> {

    public ComponentStyles(C component) {
        super(component);
    }

}
