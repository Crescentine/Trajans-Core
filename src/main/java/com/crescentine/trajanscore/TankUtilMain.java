package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;

public class TankUtilMain {
    public static boolean playerIsInTank(Player pPlayer) {
        Entity e = pPlayer.getControlledVehicle();
        if (e instanceof BaseTankEntity) {
            return true;
        }

        return false;
    }
}
