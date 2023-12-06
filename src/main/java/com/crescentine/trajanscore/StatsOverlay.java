package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Vector;

public class StatsOverlay implements IGuiOverlay {


    private static final ResourceLocation LOADING_1 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading1.png");
    private static final ResourceLocation LOADING_2 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading2.png");
    private static final ResourceLocation LOADING_3 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading3.png");
    private static final ResourceLocation LOADING_4 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading4.png");
    private static final ResourceLocation LOADING_5 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading5.png");

    private static final ResourceLocation LOADING_6 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading6.png");




     public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if(!gui.getMinecraft().isWindowActive() || gui.getMinecraft().options.hideGui)
            return;
        Player player = gui.getMinecraft().player;
        if(player == null)
            return;












        int x = screenWidth/2;
        int y = screenHeight;
        Entity entity = player.getVehicle();
        if(!(entity instanceof BaseTankEntity tank))
            return;
        Level world = player.level();
        if (!world.isClientSide) {
            return;
        }





        if (tank.time < tank.shootingCooldown) {
            float maxReloadTime = tank.shootingCooldown;
            float reloadTimeRemaining = (tank.shootingCooldown - tank.time) / 20;
            ResourceLocation[] loadingTextures = {
                    LOADING_1, LOADING_2, LOADING_3, LOADING_4, LOADING_5, LOADING_6
            };


            int numTextures = loadingTextures.length;
            int bars = (int) ((float) reloadTimeRemaining / numTextures * 6);
            int barWidth = 16;
            int barHeight = 16;
            int startX = (screenWidth - barWidth) / 2;
            int startY = screenHeight - barHeight + 30;
            for (int i = 0; i < 6; i++) {
                if (i < bars) {
                    guiGraphics.blit(loadingTextures[i], startX, startY, 0, 0, barWidth, barHeight, barWidth, barHeight);
                }
            }
        }

        DecimalFormat format1 = new DecimalFormat("0.00");
        String speed = format1.format(tank.getOverlaySpeed());
        String health = format1.format(tank.getHealth());
        guiGraphics.drawString(gui.getMinecraft().font, ChatFormatting.BOLD + "Health: " + ChatFormatting.WHITE + health, 10, 10, Color.CYAN.getRGB());
        if(TrajansCoreConfig.fuelSystemEnabled.get() && (((BaseTankEntity) entity).showFuel) && !(((BaseTankEntity) entity).isZoom) && !(gui.getMinecraft().options.getCameraType().isFirstPerson())) {
            DecimalFormat format = new DecimalFormat("0.0");
            String fuel = format.format(tank.getFuelAmount()) + "/" + format.format(tank.maxFuel);
            guiGraphics.drawString(gui.getMinecraft().font,  ChatFormatting.BOLD + "Fuel Remaining: " + ChatFormatting.WHITE + fuel, 10, 20, Color.CYAN.getRGB());
            //  mc.font.drawShadow(matrixStack, ChatFormatting.BOLD + "Speed: " + ChatFormatting.WHITE + speed + " Blocks Per Second", 10, 30, Color.CYAN.getRGB());
        }

    }



}


