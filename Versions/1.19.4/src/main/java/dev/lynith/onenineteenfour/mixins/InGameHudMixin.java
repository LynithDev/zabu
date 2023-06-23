package dev.lynith.onenineteenfour.mixins;

import dev.lynith.core.ClientStartup;
import dev.lynith.core.uiOld.hud.HudManager;
import dev.lynith.onenineteenfour.renderer.Renderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private boolean customHudInit = false;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack poseStack, float f, CallbackInfo ci) {
        Renderer.matrixStack = poseStack;
        if (!customHudInit) {
            HudManager.getInstance().getInGameHud().init();
            customHudInit = true;
        }
        HudManager.getInstance().getInGameHud().render(ClientStartup.getInstance().getBridge().getRenderer());
    }

}
