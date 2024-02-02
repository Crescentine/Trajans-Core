package com.crescentine.trajanscore.example_tank;

import com.crescentine.trajanscore.TankShootEvent;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;

public class ExampleTankEntity extends BaseTankEntity  {



    public ExampleTankEntity(EntityType<?> entityType, Level world) {
        super((EntityType<? extends BaseTankEntity>) entityType, world);
        this.health = 20.0;
        this.speedMultiplier = 0.25;
        this.shootingCooldown = 260;
        this.armor = 3.0;
        this.isTD = false;


        this.healAmount = 5;
        this.maxFuel = 12000;
        this.showFuel = true;
        this.canUseAPCR = true;
        this.canUseArmorPiercing = true;
        this.canUseHeat = true;
        this.canUseHighExplosive = true;
        this.canUseStandard = true;
    }







    protected <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if ((this.entityData.get(DATA_IS_MOVING) || this.entityData.get(DATA_IS_ROTATING)) && this.getControllingPassenger() != null && this.level().isClientSide()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }


    public ItemStack getItemStack() {
        ItemStack itemStack = getItem().getDefaultInstance();
        CompoundTag compound = new CompoundTag();
        addAdditionalSaveData(compound);
        compound.putInt("health", entityData.get(HEALTH));
        itemStack.addTagElement("EntityTag", compound);
        return itemStack;
    }


    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if(getHealth()<=0) {
            kill();
            dropItem();
        }

        return super.hurt(pSource, pAmount);
    }

    protected void dropItem() {
        ItemStack itemStack = getItemStack();
        spawnAtLocation(itemStack);
    }


    protected Item getItem() {
        return TrajansCoreItems.HAMMER.get();
    }





    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));
    }
}