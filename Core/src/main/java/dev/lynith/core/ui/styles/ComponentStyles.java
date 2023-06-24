package dev.lynith.core.ui.styles;

import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.ZabuColor;

/**
 * Placeholder class for empty component styles. Used in case a component doesn't need to have any custom styles
 * @param <C> The component type
 */
public class ComponentStyles<C extends Component<C, ComponentStyles<C>>> extends AbstractComponentStyles<C, ComponentStyles<C>> {

    public ComponentStyles(C component) {
        super(component);
    }

}
