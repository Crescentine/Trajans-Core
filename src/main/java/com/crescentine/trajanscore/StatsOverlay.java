package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.text.DecimalFormat;

public class StatsOverlay implements IGuiOverlay {

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
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
        BaseTankEntity tank = (BaseTankEntity) entity;
        DecimalFormat format1 = new DecimalFormat("0.00");
        String speed = format1.format(tank.getOverlaySpeed());
        String health = format1.format(tank.getHealth());
        guiGraphics.drawString(gui.getMinecraft().font, ChatFormatting.BOLD + "Health: " + ChatFormatting.WHITE + health, 10, 10, Color.CYAN.getRGB());
        if(TrajansCoreConfig.fuelSystemEnabled.get() && (((BaseTankEntity) entity).showFuel)) {
            DecimalFormat format = new DecimalFormat("0.0");
            String fuel = format.format(tank.getFuelAmount()) + "/" + format.format(tank.maxFuel);
            guiGraphics.drawString(gui.getMinecraft().font,  ChatFormatting.BOLD + "Fuel Remaining: " + ChatFormatting.WHITE + fuel, 10, 20, Color.CYAN.getRGB());
            //  mc.font.drawShadow(matrixStack, ChatFormatting.BOLD + "Speed: " + ChatFormatting.WHITE + speed + " Blocks Per Second", 10, 30, Color.CYAN.getRGB());
        }
    }
}

