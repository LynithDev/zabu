package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.GuiScreenChangedEvent;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.MinecraftShutdownEvent;
import dev.lynith.core.utils.GuiScreens;
import dev.lynith.core.utils.KeyboardHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At("HEAD"))
    public void startGame(CallbackInfo ci) {
        EventBus.getEventBus().post(new MinecraftInitEvent());
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void shutdown(CallbackInfo ci) {
        EventBus.getEventBus().post(new MinecraftShutdownEvent());
    }

    @Inject(method = "displayGuiScreen", at = @At("RETURN"))
    public void displayGuiScreen(GuiScreen screen, CallbackInfo ci) {
        GuiScreens screenType = GuiScreens.UNKNOWN;

        if (screen instanceof GuiMainMenu) {
            screenType = GuiScreens.MAIN_MENU;
        }

        EventBus.getEventBus().post(new GuiScreenChangedEvent(screenType));
    }
}
