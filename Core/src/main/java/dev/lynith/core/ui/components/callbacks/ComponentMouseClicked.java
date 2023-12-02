package dev.lynith.core.ui.components.callbacks;

import dev.lynith.core.ui.components.ComponentCallback;

public interface ComponentMouseClicked extends ComponentCallback {

    void invoke(int mouseX, int mouseY, int button);

}