package com.crescentine.trajanscore.mixin;


import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(ForgeGui.class)
public class ForgeGuiMixin {


    //@Inject(method = "renderChat", at = @At(value = "RETURN"), cancellable = true)
    private void renderChatMix(int width, int height, GuiGraphics guiGraphics, CallbackInfo ci) {
        final Minecraft mc = Minecraft.getInstance();
        if(mc.player.getVehicle() instanceof BaseTankEntity b && b.isZoom) {
            ci.cancel();
        }
    }
}
