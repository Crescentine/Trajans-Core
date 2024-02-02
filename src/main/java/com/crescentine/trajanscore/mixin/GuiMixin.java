package com.crescentine.trajanscore.mixin;


import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.block.RenderShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(value = Gui.class, priority = 1010)
public class GuiMixin {
    //@Shadow @Final protected Minecraft minecraft;

    //@Inject(method = "renderCrosshair", at = @At(value = "HEAD"), cancellable = true)
    private void crosshair(GuiGraphics p_282828_, CallbackInfo ci) {
//        if(this.minecraft.player.getVehicle() instanceof BaseTankEntity) {
 //           ci.cancel();
  //      }
    }
}
