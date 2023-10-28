package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class TankShootEvent extends Event {
    private final BaseTankEntity tank;

    public TankShootEvent(BaseTankEntity tank) {
        this.tank = tank;
    }

    public BaseTankEntity getTank() {
        return tank;
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(TankShootEvent.class);
    }
}
