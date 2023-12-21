package dev.lynith.core.ui.components.callbacks;

import dev.lynith.core.ui.components.ComponentCallback;

public interface ComponentMouseReleased extends ComponentCallback {

    void invoke(int mouseX, int mouseY, int button);

}
