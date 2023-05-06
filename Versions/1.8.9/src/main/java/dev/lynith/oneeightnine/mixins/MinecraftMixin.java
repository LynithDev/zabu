package dev.lynith.oneeightnine.mixins;

import dev.lynith.Core.events.EventBus;
import dev.lynith.Core.events.impl.GuiScreenChangedEvent;
import dev.lynith.Core.events.impl.MinecraftInitEvent;
import dev.lynith.Core.events.impl.MinecraftShutdownEvent;
import dev.lynith.Core.utils.GuiScreens;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
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
