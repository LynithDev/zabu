package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.input.Keyboard;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Inject(method = "setKeyPressed", at = @At("HEAD"))
    private static void setKeyBindState(int keyCode, boolean pressed, CallbackInfo ci) {
        if (pressed) {
            EventBus.getEventBus().post(new KeyPressEvent(Keyboard.translateToModern(keyCode)));
        }
    }

}
