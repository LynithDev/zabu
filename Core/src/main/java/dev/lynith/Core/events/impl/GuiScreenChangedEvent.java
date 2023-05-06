package dev.lynith.Core.events.impl;

import dev.lynith.Core.events.Event;
import dev.lynith.Core.utils.GuiScreens;
import lombok.Getter;

public class GuiScreenChangedEvent extends Event {

    @Getter
    private final GuiScreens screenType;

    public GuiScreenChangedEvent(GuiScreens screenType) {
        super(false);
        this.screenType = screenType;
    }

}
