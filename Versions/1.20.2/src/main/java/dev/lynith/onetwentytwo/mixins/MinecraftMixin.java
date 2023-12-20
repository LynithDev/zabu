package dev.lynith.onetwentytwo.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.events.impl.MinecraftGuiChanged;
import dev.lynith.core.events.impl.MinecraftInit;
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
        ClientStartup.getInstance().getEventBus().emit(MinecraftInit.class);
    }

    @Inject(method = "setScreen", at = @At("HEAD"))
    public void setScreen(Screen screen, CallbackInfo ci) {
        String clazzName = screen.getClass().getName();
        ClientStartup.getInstance().getEventBus().emit(MinecraftGuiChanged.class, clazzName);
    }

}
