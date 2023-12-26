package dev.lynith.onetwentytwo.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Platform;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.MinecraftScreenChangedEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void startGame(CallbackInfo ci) {
        Platform.getEventBus().emit(new MinecraftInitEvent());
    }

    @Inject(method = "run", at = @At("HEAD"))
    public void startClient(CallbackInfo ci) {
        ClientStartup.launch();
    }

    @Inject(method = "setScreen", at = @At("RETURN"))
    public void setScreen(Screen screen, CallbackInfo ci) {
        String clazzName = null;

        try {
            clazzName = screen.getClass().getName();
        } catch (NullPointerException ignored) {}

        Platform.getEventBus().emit(new MinecraftScreenChangedEvent(clazzName));
    }

}
