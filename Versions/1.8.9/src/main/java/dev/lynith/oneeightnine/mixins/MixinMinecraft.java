package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.Platform;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.KeyPressedEvent;
import dev.lynith.core.events.impl.KeyReleasedEvent;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.events.impl.MinecraftScreenChangedEvent;
import dev.lynith.core.input.Key;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.option.GameOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraft {

    @Shadow public GameOptions options;
    @Shadow public Screen currentScreen;

    @Inject(method = "initializeGame", at = @At("RETURN"))
    public void startGame(CallbackInfo ci) {
        Platform.eventBus.emit(new MinecraftInitEvent());
    }

    @Inject(method = "handleKeyInput", at = @At("HEAD"))
    public void handleKeyInput(CallbackInfo ci) {
        int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() : Keyboard.getEventKey();
        if (i != 0 && !Keyboard.isRepeatEvent()) {
            if (!(this.currentScreen instanceof ControlsOptionsScreen) || ((ControlsOptionsScreen) this.currentScreen).time <= MinecraftClient.getTime() - 20L) {
                Key key = dev.lynith.core.input.Keyboard.INSTANCE.translateToModern(i);
                if (Keyboard.getEventKeyState()) {
                    Platform.eventBus.emit(new KeyPressedEvent(key));
                } else {
                    Platform.eventBus.emit(new KeyReleasedEvent(key));
                }
            }
        }
    }

    @Inject(method = "getMaxFramerate", at = @At("HEAD"), cancellable = true)
    public void getMaxFramerate(CallbackInfoReturnable<Integer> cir) {
        int fps;

        dev.lynith.core.ui.components.Screen screen = dev.lynith.core.ui.components.Screen.getCurrentScreen();
        if (screen != null) {
            fps = screen.getMaxFramerate();
        } else if (MinecraftClient.getInstance().world == null && MinecraftClient.getInstance().currentScreen != null) {
            fps = 30;
        } else {
            fps = options.maxFramerate;
        }

        cir.setReturnValue(fps);
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
