package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.text.DecimalFormat;

public class StatsOverlay {
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        if(!mc.isWindowActive() || mc.options.hideGui)
            return;

        Player player = mc.player;
        if(player == null)
            return;

        Entity entity = player.getVehicle();
        if(!(entity instanceof BaseTankEntity))
            return;

        PoseStack matrixStack = new PoseStack();
        BaseTankEntity tank = (BaseTankEntity) entity;
        DecimalFormat format1 = new DecimalFormat("0.0");
        String speed = format1.format(tank.getTankSpeed() * 43.2);
        String health = format1.format(tank.getHealth());
        mc.font.drawShadow(matrixStack, ChatFormatting.BOLD + "Health: " + ChatFormatting.WHITE + health, 10, 10, Color.CYAN.getRGB());

        if(TrajansCoreConfig.fuelSystemEnabled.get() && (((BaseTankEntity) entity).showFuel)) {
            DecimalFormat format = new DecimalFormat("0.0");
            String fuel = format.format(tank.getFuelAmount()) + "/" + format.format(tank.maxFuel);
            mc.font.drawShadow(matrixStack, ChatFormatting.BOLD + "Fuel Remaining: " + ChatFormatting.WHITE + fuel, 10, 20, Color.CYAN.getRGB());
        }
        mc.font.drawShadow(matrixStack, ChatFormatting.BOLD + "Blocks Per Second: " + ChatFormatting.WHITE + speed, 10, 30, Color.CYAN.getRGB());
    }
}
