package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Platform;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.MinecraftScreenChangedEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraft {

    @Inject(method = "initializeGame", at = @At("RETURN"))
    public void startGame(CallbackInfo ci) {
        Platform.eventBus.emit(new MinecraftInitEvent());
    }

    @Inject(method = "setPixelFormat", at = @At("RETURN"))
    public void setPixelFormat(CallbackInfo ci) {
        ClientStartup.launch();
    }

    @Inject(method = "setScreen", at = @At("RETURN"))
    public void setScreen(Screen screen, CallbackInfo ci) {
        String clazz;

        try {
            clazz = screen.getClass().getName();
        } catch (NullPointerException ignored) {
            clazz = null;
        }

        Platform.eventBus.emit(new MinecraftScreenChangedEvent(clazz));
    }

}
