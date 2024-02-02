package com.crescentine.trajanscore.mixin;


import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeGui.class)
public class ForgeGuiMixin {


    @Inject(method = "renderChat", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private void renderChatMix(int width, int height, GuiGraphics guiGraphics, CallbackInfo ci) {
        //guiGraphics.fill(RenderType.guiOverlay(), 0, 0, width, height, -90, 0); // The last argument '0' is an alpha value, use 0 for full transparency
        final Minecraft mc = Minecraft.getInstance();
        /*
            ci.cancel();
        */
    }
}
