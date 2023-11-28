package dev.lynith.oneeightnine;

import dev.lynith.core.bridge.IMinecraft;
import dev.lynith.core.bridge.IVersion;
import dev.lynith.core.bridge.gui.IGui;
import dev.lynith.oneeightnine.gui.Gui;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;

@Getter
public class Version implements IVersion {

    private final IMinecraft minecraft = new Minecraft();

    private final IGui renderer = new Gui();

}
