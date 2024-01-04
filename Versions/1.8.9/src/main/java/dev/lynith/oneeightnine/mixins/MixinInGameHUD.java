package dev.lynith.oneeightnine.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.lynith.core.hud.InGameHUD;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHUD {

    @Unique
    private final InGameHUD client$hud = new InGameHUD();

    @Inject(method = "render", at = @At("TAIL"))
    private void client$render(float f, CallbackInfo ci) {
        client$hud.render(f);
    }

}
