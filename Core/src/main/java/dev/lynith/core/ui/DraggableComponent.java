package dev.lynith.core.ui;

import lombok.Getter;
import lombok.Setter;

public abstract class DraggableComponent extends Component {

    @Getter
    private boolean dragging = false;

    private int offX, offY;

    @Override
    public MouseCallback getOnClick() {
        return (mouseX, mouseY) -> {
            this.dragging = true;
            this.offX = mouseX - getX();
            this.offY = mouseY - getY();
        };
    }

    @Override
    public MouseCallback getOnDrag() {
        return (mouseX, mouseY) -> {
            setX(mouseX - this.offX);
            setY(mouseY - this.offY);
        };
    }
}
