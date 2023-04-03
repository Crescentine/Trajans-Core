package com.crescentine.trajanscore.example_tank;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class ExampleTankEntity extends BaseTankEntity  {
    public ExampleTankEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
        this.health = 20.0;
        this.speedMultiplier = 0.25;
        this.shootingCooldown = 20;
        this.armor = 3.0;
        this.healAmount = 5;
        this.maxFuel = 12000;
        this.showFuel = true;
        this.canUseAPCR = true;
        this.canUseArmorPiercing = true;
        this.canUseHeat = true;
        this.canUseHighExplosive = true;
        this.canUseStandard = true;
    }
}