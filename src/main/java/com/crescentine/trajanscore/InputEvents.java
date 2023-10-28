package com.crescentine.trajanscore;

import com.crescentine.trajanscore.packet.FuelRemainingPacket;
import com.crescentine.trajanscore.packet.TankPacket;
import com.crescentine.trajanscore.packet.TrajansCoreNetwork;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TrajansCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.screen != null || mc.level == null) return;
        onInput(mc, event.getKey(), event.getAction());
    }

    private static void onInput(Minecraft mc, int key, int action) {
        if (mc.screen == null && TankModClient.SHOOT_KEY.consumeClick()) {
            TrajansCoreNetwork.TANK.sendToServer(new TankPacket(key));
        }
        if (mc.screen == null && TankModClient.FUEL_CHECK.consumeClick()) {
            TrajansCoreNetwork.FUEL_REMAINING.sendToServer(new FuelRemainingPacket(key));
        }
    }




}