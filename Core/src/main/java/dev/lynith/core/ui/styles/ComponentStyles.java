package dev.lynith.core.ui.styles;

import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.ZabuColor;
import lombok.Getter;

public class ComponentStyles<C extends Component<C, ?>, S extends ComponentStyles<C, S>> {

    @Getter
    private final C component;

    public ComponentStyles(C component) {
        this.component = component;
    }

    /**
     * The padding of the component
     * @see Spacing
     */
    public Spacing padding = new Spacing();

    /**
     * The margin of the component
     * @see Spacing
     */
    public Spacing margin = new Spacing();

    /**
     * The border of the component
     * @see Border
     */
    public Border border = new Border();

    /**
     * Whether the border should affect the bounding box of the component
     */
    public boolean borderBoundingBox = false;

    /**
     * The background color of the component
     */
    public ZabuColor background = ZabuColor.from(0);

}
