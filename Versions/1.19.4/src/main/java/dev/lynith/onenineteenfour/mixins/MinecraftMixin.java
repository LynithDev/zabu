package dev.lynith.onenineteenfour.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.GuiScreenChangedEvent;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.MinecraftShutdownEvent;
import dev.lynith.core.utils.GuiScreens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {

    @Inject(method = "run", at = @At("HEAD"))
    public void run(CallbackInfo info) {
        EventBus.getEventBus().post(new MinecraftInitEvent());
    }

    @Inject(method = "setScreen", at = @At("RETURN"))
    public void setScreen(Screen screen, CallbackInfo info) {
        GuiScreens type = GuiScreens.UNKNOWN;

        if (screen instanceof TitleScreen) {
            type = GuiScreens.MAIN_MENU;
        }

        EventBus.getEventBus().post(new GuiScreenChangedEvent(type));
    }

    @Inject(method = "stop", at = @At("HEAD"))
    public void shutdown(CallbackInfo ci) {
        EventBus.getEventBus().post(new MinecraftShutdownEvent());
    }

}
