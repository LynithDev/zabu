package dev.lynith.onenineteenfour.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.input.Key;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardHandlerMixin {

    private boolean pressed = false;

    @Inject(method = "onKey", at = @At("HEAD"))
    public void keyPress(long window, int key, int scancode, int modifiers, int j, CallbackInfo info) {
        // field_31997 == PRESS
        if (modifiers == InputUtil.field_31997 && !this.pressed) {
            this.pressed = true;
            EventBus.getEventBus().post(new KeyPressEvent(new Key(key)));
        } else if (modifiers != InputUtil.field_31997 && this.pressed) {
            this.pressed = false;
        }
    }

}
