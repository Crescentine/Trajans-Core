package com.crescentine.trajanscore;


import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TrajansCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class TankEvents {

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinLevelEvent e) {
        if (e.getEntity() instanceof BaseTankEntity b) {
            //b.setHealth(b.health);
        }
    }
}
