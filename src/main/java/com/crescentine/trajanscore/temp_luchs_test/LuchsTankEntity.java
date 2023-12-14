package com.crescentine.trajanscore.temp_luchs_test;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class LuchsTankEntity extends BaseTankEntity  {

    public LuchsTankEntity(EntityType<?> entityType, Level world) {
        super((EntityType<? extends BaseTankEntity>) entityType, world);
        this.health = 60.0;
        this.speedMultiplier = 0.625;
        this.shootingCooldown = 20;
        this.armor = 3.0;
        this.isTD = false;


        this.healAmount = 5;
        this.maxFuel = 12000;
        this.showFuel = true;
        this.canUseAPCR = false;
        this.canUseArmorPiercing = false;
        this.canUseHeat = false;
        this.canUseHighExplosive = false;
        this.canUseLowCaliberShell = true;
        this.canUseStandard = false;
    }



    protected <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving() && this.getControllingPassenger()!=null) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;

    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));
    }
}