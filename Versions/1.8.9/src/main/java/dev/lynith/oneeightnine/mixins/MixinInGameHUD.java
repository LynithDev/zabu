package dev.lynith.oneeightnine.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.lynith.core.ui.screens.InGameHUD;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHUD {

    @Unique
    private final InGameHUD client$hud = new InGameHUD();

    @Inject(method = "render", at = @At("RETURN"))
    public void render(float f, CallbackInfo ci) {
        client$hud.toMCScreen().render(0, 0, f);
        GlStateManager.enableAlphaTest();
        GlStateManager.enableBlend();
    }

}
