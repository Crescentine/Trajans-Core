package com.crescentine.trajanscore.basetank;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class AnimatedTankEntity extends Entity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected AnimatedTankEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }



    protected boolean isMoving() {
        return this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }




    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }


    @OnlyIn(Dist.CLIENT)
    public abstract void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements);

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public boolean isNoGravity() {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }


    @Override
    public boolean canBeRiddenUnderFluidType(FluidType type, Entity rider) {
        return false;
    }



    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {


    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


}
