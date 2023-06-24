package dev.lynith.core.ui.styles;

import dev.lynith.core.ui.Component;
import dev.lynith.core.utils.ZabuColor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * The base class for all component styles
 * @param <C> The component type
 * @param <S> The self style type
 */
@Accessors(fluent = true)
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

    public void setPadding(int top, int left, int bottom, int right) {
        this.padding = new Spacing(top, left, bottom, right);
    }

    public void setPadding(int topBottom, int leftRight) {
        this.padding = new Spacing(topBottom, leftRight);
    }

    public void setPadding(int all) {
        this.padding = new Spacing(all);
    }

    public void setPadding(Spacing padding) {
        this.padding = padding;
    }

    public void setMargin(int top, int left, int bottom, int right) {
        this.margin = new Spacing(top, left, bottom, right);
    }

    public void setMargin(int topBottom, int leftRight) {
        this.margin = new Spacing(topBottom, leftRight);
    }

    public void setMargin(int all) {
        this.margin = new Spacing(all);
    }

    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

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

    /**
     * Declares whether the component is draggable or not
     */
    private boolean draggable = false;
}
