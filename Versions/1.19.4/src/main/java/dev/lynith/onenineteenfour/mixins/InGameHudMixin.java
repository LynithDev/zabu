package dev.lynith.onenineteenfour.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.lynith.core.ClientStartup;
import dev.lynith.core.ui.hud.HudManager;
import dev.lynith.onenineteenfour.renderer.Renderer;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {

    private boolean customHudInit = false;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(PoseStack poseStack, float f, CallbackInfo ci) {
        Renderer.matrixStack = poseStack;
        if (!customHudInit) {
            HudManager.getInstance().getInGameHud().init();
            customHudInit = true;
        }
        HudManager.getInstance().getInGameHud().render(ClientStartup.getInstance().getBridge().getRenderer());
    }

}
