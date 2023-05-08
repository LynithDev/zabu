package dev.lynith.core.events.impl;

import dev.lynith.core.events.Event;
import dev.lynith.core.utils.GuiScreens;
import lombok.Getter;

public class GuiScreenChangedEvent extends Event {

    @Getter
    private final GuiScreens screenType;

    public GuiScreenChangedEvent(GuiScreens screenType) {
        super(false);
        this.screenType = screenType;
    }

}
