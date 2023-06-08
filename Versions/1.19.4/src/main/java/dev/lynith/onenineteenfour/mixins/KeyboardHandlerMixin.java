package dev.lynith.onenineteenfour.mixins;

import com.mojang.blaze3d.platform.InputConstants;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.KeyPressEvent;
import dev.lynith.core.utils.Key;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyMapping.class)
public class KeyboardHandlerMixin {

    @Shadow
    private InputConstants.Key key;

    private boolean pressed = false;

    @Inject(method = "setDown", at = @At("RETURN"))
    public void keyPress(boolean pressed, CallbackInfo info) {
        if (pressed && !this.pressed) {
            this.pressed = true;
            EventBus.getEventBus().post(new KeyPressEvent(new Key(key.getValue())));
        } else if (!pressed && this.pressed) {
            this.pressed = false;
        }
    }

}
