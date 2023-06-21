package dev.lynith.onenineteenfour.mixins;

import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.ChatSendEvent;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"))
    public void sendChatMessage(String message, CallbackInfo info) {
        EventBus.getEventBus().post(new ChatSendEvent(message));
    }

}
