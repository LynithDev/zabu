package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.utils.KeyboardHelper;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Inject(method = "setKeyBindState", at = @At("HEAD"))
    private static void setKeyBindState(int keyCode, boolean pressed, CallbackInfo ci) {
        if (pressed) {
            EventBus.getEventBus().post(new KeyPressEvent(KeyboardHelper.translateToModern(keyCode)));
        }
    }

}
