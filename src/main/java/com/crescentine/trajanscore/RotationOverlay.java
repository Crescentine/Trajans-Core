package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class RotationOverlay {
    /*private static final ResourceLocation TANK_HULL = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/tank_hull.png");
    private static final ResourceLocation TANK_TURRET = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/tank_turret.png");

    private static final ResourceLocation TANK_SCOPE = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/scopev2.png");

    float scopeScale = 0.5f;

    public static void renderScopeOverlay(GuiGraphics guiGraphics, float v) {
        float f = (float) Math.min(guiGraphics.guiWidth(), guiGraphics.guiHeight());
        float f1 = Math.min((float) guiGraphics.guiWidth() / f, (float) guiGraphics.guiHeight() / f) * v;
        int i = Mth.floor(f * f1);
        int j = Mth.floor(f * f1);
        int k = (guiGraphics.guiWidth() - i) / 2;
        int l = (guiGraphics.guiHeight() - j) / 2;
        int i1 = k + i;
        int j1 = l + j;


        guiGraphics.blit(TANK_SCOPE, k, l, -90, 0.0F, 0.0F, i, j, i, j);
        guiGraphics.fill(RenderType.guiOverlay(), 0, j1, guiGraphics.guiWidth(), guiGraphics.guiHeight(), -90, -16777216);
        guiGraphics.fill(RenderType.guiOverlay(), 0, 0, guiGraphics.guiWidth(), l, -90, -16777216);
        guiGraphics.fill(RenderType.guiOverlay(), 0, l, k, j1, -90, -16777216);
        guiGraphics.fill(RenderType.guiOverlay(), i1, l, guiGraphics.guiWidth(), j1, -90, -16777216);
    }





    public static final IGuiOverlay OVERLAY = ((gui, poseStack, partialTick, width, height) -> {
        
        int x = width / 2;
        int y = height;


        if(!gui.getMinecraft().isWindowActive() || gui.getMinecraft().options.hideGui)
            return;
        Player player = gui.getMinecraft().player;
        if(player == null)
            return;

        Entity entity = player.getVehicle();
        if(!(entity instanceof BaseTankEntity))
            return;
        Level world = player.level();
        if (!world.isClientSide) {
            return;
        }

        if((((BaseTankEntity) entity).showFuel) && ((BaseTankEntity) entity).isZoom && gui.getMinecraft().options.getCameraType().isFirstPerson() ) {

            renderScopeOverlay(poseStack,1);

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, TANK_HULL);


            poseStack.pose().pushPose();
            int px = x+120;
            int yx = y-54;

            int pivx = px+32;
            int pivyx = yx+32;

            //poseStack.pose().translate(pivx, pivyx, 0);
            //poseStack.pose().mulPose(Axis.ZN.rotationDegrees(entity.getYRot()));
            //poseStack.pose().translate(-pivx, -pivyx, 0);
            poseStack.blit(TANK_HULL, px, yx, 0, 0, 64, 64, 64, 64);


            poseStack.pose().popPose();


            RenderSystem.setShaderTexture(0, TANK_TURRET);

            poseStack.pose().pushPose();
            int pxTurret = x+120;
            int pyTurret = y-54;

            int pivpxTurret = pxTurret+32;
            int pivpyTurret = pyTurret+32;
            poseStack.pose().translate(pivpxTurret, pivpyTurret, 0);
            poseStack.pose().mulPose(Axis.ZP.rotationDegrees(entity.getControllingPassenger().getYHeadRot()-entity.getYRot()));
            poseStack.pose().translate(-pivpxTurret, -pivpyTurret, 0);

            for (int i = 0; i < 10; i++) {
                poseStack.blit(TANK_TURRET, pxTurret, pyTurret, 0, 0, 64, 64, 64, 64);
            }

            poseStack.pose().popPose();
        }

    });*/

}
