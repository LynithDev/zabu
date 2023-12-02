package dev.lynith.oneeightnine;

import dev.lynith.core.bridge.IMinecraft;
import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.bridge.gui.IRenderer;
import dev.lynith.oneeightnine.gui.Renderer;
import lombok.Getter;

@Getter
public class Version implements IVersion {

    private final IMinecraft minecraft = new Minecraft();

    private final IRenderer renderer = new Renderer();

}
