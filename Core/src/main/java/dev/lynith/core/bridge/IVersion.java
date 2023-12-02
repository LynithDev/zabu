package dev.lynith.core.bridge;

import dev.lynith.core.bridge.gui.IRenderer;

public interface IVersion {

    IMinecraft getMinecraft();

    IRenderer getRenderer();

}
