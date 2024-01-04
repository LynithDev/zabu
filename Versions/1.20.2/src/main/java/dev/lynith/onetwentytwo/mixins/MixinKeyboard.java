package dev.lynith.onetwentytwo.mixins;

import dev.lynith.core.Platform;
import dev.lynith.core.events.impl.KeyPressedEvent;
import dev.lynith.core.events.impl.KeyReleasedEvent;
import dev.lynith.core.input.Key;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(method = "onKey", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/util/InputUtil;fromKeyCode(II)Lnet/minecraft/client/util/InputUtil$Key;", shift = At.Shift.AFTER))
    public void onKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        InputUtil.Key inputKey = InputUtil.fromKeyCode(key, scancode);

        if (inputKey != null) {
            Key zabuKey = new Key(inputKey.getCode());
            if (i == 1) {
                Platform.eventBus.emit(new KeyPressedEvent(zabuKey));
            } else if (i == 0) {
                Platform.eventBus.emit(new KeyReleasedEvent(zabuKey));
            }
        }
    }
}
