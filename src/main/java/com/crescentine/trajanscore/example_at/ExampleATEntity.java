package com.crescentine.trajanscore.example_at;

import com.crescentine.trajanscore.basetank.BaseATEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import oshi.jna.platform.unix.CLibrary;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ExampleATEntity extends BaseATEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public ExampleATEntity(EntityType<? extends BaseATEntity> entityType, Level world) {
        super(entityType, world);
        this.health = 100;
        this.entityData.set(HEALTH, (int) this.health);
        this.armor = 3.0;
        this.shootingCooldown = 180;
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
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}