package dev.lynith.core.ui.styles;

import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.ZabuColor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractComponentStyles<C extends Component<C, S>, S extends AbstractComponentStyles<C, S>> {

    @Getter
    private final C component;

    public AbstractComponentStyles(C component) {
        this.component = component;
    }

    /**
     * The padding of the component
     * @see Spacing
     */
    private Spacing padding = new Spacing();

    /**
     * The margin of the component
     * @see Spacing
     */
    private Spacing margin = new Spacing();

    /**
     * The border of the component
     * @see Border
     */
    private Border border = new Border();

    /**
     * Whether the border should affect the bounding box of the component
     */
    private boolean borderBoundingBox = false;

    /**
     * The background color of the component
     */
    private ZabuColor background = ZabuColor.from(0);

}
