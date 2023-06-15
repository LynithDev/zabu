package dev.lynith.oneeightnine.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.hud.HudManager;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private boolean customHudInit = false;

    @Inject(method = "renderBossBar", at = @At("RETURN"))
    private void renderGameOverlay(CallbackInfo ci) {
        // No proper init method in Gui so have to resort to this
        if (!customHudInit) {
            HudManager.getInstance().getInGameHud().init();
            customHudInit = true;
        }
        HudManager.getInstance().getInGameHud().render(ClientStartup.getInstance().getBridge().getRenderer());
    }

}
