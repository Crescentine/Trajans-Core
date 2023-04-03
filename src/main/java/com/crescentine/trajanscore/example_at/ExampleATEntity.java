package com.crescentine.trajanscore.example_at;

import com.crescentine.trajanscore.basetank.BaseATEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class ExampleATEntity extends BaseATEntity implements GeoEntity {
    public ExampleATEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
        this.health = 20;
        this.armor = 3.0;
        this.shootingCooldown = 20;
        this.canUseAPCR = true;
        this.canUseArmorPiercing = true;
        this.canUseHeat = true;
        this.canUseHighExplosive = true;
        this.canUseStandard = true;
    }
    @Override
    public double getPassengersRidingOffset() {
        return 0.3;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }
}