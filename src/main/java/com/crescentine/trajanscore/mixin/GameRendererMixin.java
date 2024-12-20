package com.crescentine.trajanscore.mixin;


import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
@Mixin(GameRenderer.class)

public abstract class GameRendererMixin {


    @Shadow @Final private Minecraft minecraft;

    @Inject(at = @At("RETURN"), method = "getFov", cancellable = true)
    private void fovMod(Camera pActiveRenderInfo, float pPartialTicks, boolean pUseFOVSetting, CallbackInfoReturnable<Double> cir) {
        double defaultFOV = cir.getReturnValue();
        if (minecraft.player.getVehicle() instanceof BaseTankEntity tank && tank.isZoom && minecraft.options.getCameraType().isFirstPerson()) {
            cir.setReturnValue(8D);
        }
    }


}
*/
