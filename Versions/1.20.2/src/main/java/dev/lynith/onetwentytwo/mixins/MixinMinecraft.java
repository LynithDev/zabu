package dev.lynith.onetwentytwo.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lynith.core.ClientStartup;
import dev.lynith.core.Platform;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.MinecraftScreenChangedEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MixinMinecraft {

    @Shadow public ClientWorld world;

    @Shadow public Screen currentScreen;

    @Shadow private Overlay overlay;

    @Shadow @Final private Window window;

    @Shadow public GameOptions options;

    @Inject(method = "tick", at = @At("HEAD"))
    public void startGame(CallbackInfo ci) {
        Platform.eventBus.emit(new MinecraftInitEvent());
    }

    @Inject(method = "getFramerateLimit", at = @At("HEAD"), cancellable = true)
    public void getFramerateLimit(CallbackInfoReturnable<Integer> cir) {
        int fps;

        dev.lynith.core.ui.components.Screen screen = dev.lynith.core.ui.components.Screen.getCurrentScreen();
        if (screen != null) {
            fps = screen.getMaxFramerate();
        } else if (this.world != null || this.currentScreen == null && this.overlay == null) {
            fps = this.options.getMaxFps().getValue();
        } else {
            fps = 60;
        }

        cir.setReturnValue(fps);
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

        Platform.eventBus.emit(new MinecraftScreenChangedEvent(clazzName));
    }

}
