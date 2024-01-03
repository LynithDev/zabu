package dev.lynith.onetwentytwo.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lynith.core.ui.screens.InGameHUD;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHUD {

    @Unique
    private InGameHUD client$hud;

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext drawContext, float f, CallbackInfo ci) {
        if (client$hud == null)
            client$hud = new InGameHUD();

        client$hud.toMCScreen().render(0, 0, f);
    }

}
