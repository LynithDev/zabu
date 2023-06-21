package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.ChatSendEvent;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"))
    public void onSendChatMessage(String message, CallbackInfo ci) {
        EventBus.getEventBus().post(new ChatSendEvent(message));
    }

}
