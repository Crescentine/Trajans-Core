package com.crescentine.trajanscore.basetrackedvehicle;


import com.crescentine.trajanscore.basetank.AnimatedTankEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;




import com.crescentine.trajanscore.TrajansCoreConfig;


import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;


// Completely bare-bones tracked vehicle entity primarily to be used for addons.

/*
public class BaseTrackedVehicle extends AnimatedTankEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public double healAmount = 0;
    public double speedMultiplier = 0;
    private static final EntityDataAccessor<Integer> FUEL_AMOUNT = SynchedEntityData.defineId(com.crescentine.trajanscore.basetank.BaseTankEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(com.crescentine.trajanscore.basetank.BaseTankEntity.class, EntityDataSerializers.FLOAT);







    public float time;
    public double health = 0;

    public int age;

    public double coalFuelAmount = TrajansCoreConfig.coalFuelAmount.get();
    public double lavaFuelAmount = TrajansCoreConfig.lavaFuelAmount.get();
    public double maxFuel = 12000.00;
    double d0 = this.random.nextGaussian() * 0.03D;
    double d1 = this.random.nextGaussian() * 0.03D;
    double d2 = this.random.nextGaussian() * 0.03D;
    private static final Ingredient FUELS = Ingredient.of(Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK);
    private static final Ingredient COAL_FUEL = Ingredient.of(Items.COAL, Items.CHARCOAL);
    private static final Ingredient COAL_BLOCK_FUEL = Ingredient.of(Items.COAL_BLOCK);
    private static final Ingredient LAVA_FUEL = Ingredient.of(Items.LAVA_BUCKET);
    private static final Ingredient HEALS = Ingredient.of(Items.IRON_BLOCK, Items.IRON_INGOT);
    private ImmutableList<Entity> passengers = ImmutableList.of();

    public int accelerationTime;
    public int timeInVehicle;
    public double blocksPerSecond = 0.00;







    public BaseTrackedVehicle(EntityType<?> entityType, Level world) {
        super((EntityType<? extends LivingEntity>) entityType, world);
    }



    public static AttributeSupplier.Builder createAttributes() {
        return Pig.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 250.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.FOLLOW_RANGE, 0.0D);
    }
    @Override
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.getHealth() < this.health) {
            if (HEALS.test(itemstack)) {
                if (itemstack.is(Items.IRON_BLOCK)) {
                    healTank(healAmount);
                    if (itemstack.is(Items.IRON_INGOT)) {
                        healTank(healAmount / 9);
                    }
                    itemstack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (this.isAlive()) {
        }
        if (getFuelAmount() < maxFuel && FUELS.test(itemstack)) {
            if (COAL_FUEL.test(itemstack)) {
                fuelTankWithItem((int) coalFuelAmount * 20);
                itemstack.shrink(1);
            }
            if (COAL_BLOCK_FUEL.test(itemstack)) {
                fuelTankWithItem((int) coalFuelAmount * 180);
                itemstack.shrink(1);
            }
            if (LAVA_FUEL.test(itemstack)) {
                fuelTankWithItem((int) lavaFuelAmount * 20);
                player.getInventory().add(itemstack.getItem().getCraftingRemainingItem().getDefaultInstance());
                itemstack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        if (canAddPassenger(player)) {
            player.startRiding(this, true);
            return InteractionResult.FAIL;
        }
        return InteractionResult.FAIL;
    }



    public void healTank(double healAmount) {
        if (this.getMaxHealth() - this.getHealth() > healAmount) {
            this.setHealth((float) this.getHealth() + (float) healAmount);
            this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        } else {
            if (this.getHealth() < this.getMaxHealth()) {
                this.setHealth(this.getMaxHealth());
                this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        }
    }

    public void fuelTankWithItem(int fuelAmount) {
        // Stops overfueling
        if (getFuelAmount() + fuelAmount < maxFuel) {
            setFuelAmount(getFuelAmount() + fuelAmount);
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ(), d0, d1, d2);
        } else {
            if (getFuelAmount() < maxFuel) {
                setFuelAmount((int) maxFuel);
                this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ(), d0, d1, d2);
            }
        }
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return this.passengers.isEmpty();
    }

    @Override
    protected boolean isImmobile() {
        return false;
    }

    @Override
    public int getMaxFallDistance() {
        return 30;
    }







    @Override
    public void travel(Vec3 pos) {
        if (this.isAlive()) {
            if (this.isVehicle()) {
                LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();


                if (level().isClientSide() && this.getFuelAmount() > 0) {
                    if (livingentity.isPassenger()) {


                        float f = livingentity.xxa * 0.5F;
                        float f1 = livingentity.zza;

                        if (getFuelAmount() > 0) {
                            if (f1 < 0) {
                                this.setSpeed(Math.max((float) speedMultiplier * (accelerationTime / 160.0f) * 0.5f, (float) (speedMultiplier * 0.25f)));
                            } else {
                                this.setSpeed(Math.max((float) speedMultiplier * (accelerationTime / 160.0f), (float) (speedMultiplier * 0.25f)));
                            }
                        } else {
                            this.setSpeed(0.0f);
                        }

                        this.setYRot(this.getYRot() - f * 5.0F);
                        this.yRotO = this.getYRot();
                        this.setXRot(this.getXRot() * 0.5F);
                        this.setRot(this.getYRot(), this.getXRot() * 0.5F);
                        this.yBodyRot = this.getYRot();
                        this.yHeadRot = this.yBodyRot;



                        this.setRot(this.getYRot(), this.getXRot());


                        super.travel(new Vec3(0, pos.y, livingentity.zza));
                    } else {
                        super.travel(pos);
                        this.setSpeed(0f);
                    }
                }
            }
        }
    }




    @Override
    public boolean shouldRiderSit() {
        return false;
    }



    @Override
    public boolean startRiding(Entity p_21396_, boolean p_21397_) {
        this.time = 0;
        return super.startRiding(p_21396_, p_21397_);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_EXPLODE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }


    @Override
    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.PLAYER_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.GENERIC_SWIM;
    }

    @Override
    public void tick() {
        super.tick();
        fuelTick();
        accelerationTick();
        age++;

        if (this.isVehicle()) {
            timeInVehicle++;
        }
        if (timeInVehicle % 20 == 0) {
            blocksPerSecond = this.getDeltaMovement().horizontalDistance() * 20;
        }
    }

    protected void fuelTick() {
        int fuel = getFuelAmount();
        if (this.isMoving() && this.isVehicle() && fuel > 0) {
            removeFuel(1);
        }
    }

    protected void accelerationTick() {
        if (this.isMoving() && this.isVehicle() && accelerationTime <= 160) {
            accelerationTime++;
        }

        if (accelerationTime >= 0) {
            // Decrease Acceleration Time when not moving OR not in tank OR no fuel
            if (!this.isVehicle() || !this.isMoving() || getFuelAmount() == 0) {
                accelerationTime -= 2;
                if (accelerationTime < 0) {
                    accelerationTime = 0;
                }
            }
        }
        age++;
        if (level().isClientSide() && this.isVehicle() && this.age % 10 == 0 && getFuelAmount() > 0) {
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ(), d0, d1, d2);
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ(), d0, d1, d2);
        }
    }


    private void removeFuel(int amount) {
        int fuel = getFuelAmount();
        int newFuel = fuel - amount;
        setFuelAmount(newFuel);
    }

    private void addFuel(int amount) {
        int fuel = getFuelAmount();
        int newFuel = fuel + amount;
        setFuelAmount((newFuel));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(FUEL_AMOUNT, 0);
        entityData.define(SPEED, 0.0f);
    }

    public void setFuelAmount(int fuel) {
        this.entityData.set(FUEL_AMOUNT, fuel);
    }

    public int getFuelAmount() {
        return this.entityData.get(FUEL_AMOUNT);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setFuelAmount(pCompound.getInt("fuel"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("fuel", getFuelAmount());
    }







    public boolean fuelLeft(Player player) {
        double fuel = getFuelAmount();
        if (fuel < 1200 && fuel > 1 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("Low fuel! Amount of time before fuel runs out " + fuel / 20 + " seconds or " + String.format("%.2f", fuel / 1200) + " minutes ").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return true;
        }
        if (fuel > 1200 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("The amount of fuel remaining: " + fuel / 20 + " seconds, or " + String.format("%.2f", fuel / 1200) + " minutes ").withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD), false);
            return true;
        }
        if (getFuelAmount() < 1 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("No fuel remaining!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return true;
        }
        if (!TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("Fuel is not enabled! Enable fuel in the config, or contact your server administrator.").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return true;
        }
        return false;
    }

    @Override
    public float getStepHeight() {
        return 1.0f;
    }

    public boolean canBeAffected(@NotNull MobEffectInstance pPotioneffect) {
        return false;
    }

    public boolean isMoving() {
        return this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    @Override
    public boolean fireImmune() {
        return TrajansCoreConfig.tanksImmuneToFire.get();
    }




    @Override
    protected void removePassenger(Entity pPassenger) {
        this.setSpeed(0f);
        this.timeInVehicle = 0;
        super.removePassenger(pPassenger);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : (LivingEntity) this.getPassengers().get(0);
    }

    @Override
    public void stopRiding() {
        this.setSpeed(0f);
        super.stopRiding();
    }




}
*/