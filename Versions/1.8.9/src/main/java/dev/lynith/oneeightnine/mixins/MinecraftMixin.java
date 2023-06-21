package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.events.EventBus;
import dev.lynith.core.events.impl.GuiScreenChangedEvent;
import dev.lynith.core.events.impl.MinecraftInitEvent;
import dev.lynith.core.utils.GuiScreens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {

    @Inject(method = "initializeGame", at = @At("RETURN"))
    public void startGame(CallbackInfo ci) {
        EventBus.getEventBus().post(new MinecraftInitEvent());
    }

    @Inject(method = "setScreen", at = @At("RETURN"))
    public void displayGuiScreen(Screen screen, CallbackInfo ci) {
        GuiScreens screenType = GuiScreens.UNKNOWN;

        for (Map.Entry<GuiScreens, Object> entry : ClientStartup.getInstance().getBridge().getGame().getGuiScreens().entrySet()) {
            if (entry != null && screen != null && entry.getValue().equals(screen.getClass())) {
                screenType = entry.getKey();
                break;
            }
        }

        if (screenType == null) {
            screenType = GuiScreens.UNKNOWN;
        }

        EventBus.getEventBus().post(new GuiScreenChangedEvent(screenType));
    }
}
