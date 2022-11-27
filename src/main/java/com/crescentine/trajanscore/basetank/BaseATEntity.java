package com.crescentine.trajanscore.basetank;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShell;
import com.crescentine.trajanscore.tankshells.standard.StandardShell;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BaseATEntity extends AnimatedTankEntity {
    public boolean canUseAPCR;
    public boolean canUseStandard;
    public boolean canUseArmorPiercing;
    public boolean canUseHeat;
    public boolean canUseHighExplosive;
    public int shootingCooldown = 60;
    public int time;
    public double armor = 0;
    static int shellsUsed = 1;
    public double health = 0;
    public double speedMultiplier = 0;

    public BaseATEntity(EntityType<?> entityType, Level world) {
        super((EntityType<? extends Animal>) entityType, world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Pig.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 250.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0)
                .add(Attributes.FOLLOW_RANGE, 0.0D);
    }
    @Override
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand hand) {
        player.startRiding(this, true);
        return InteractionResult.SUCCESS;
    }
    @Override
    protected boolean canAddPassenger(Entity entity) {
        return !this.isVehicle();
    }
    @Override
    public boolean shouldRiderSit() {
        return true;
    }
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((float) health);
        this.getAttribute(Attributes.ARMOR).setBaseValue(armor);
        this.setHealth((float) health);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
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
    protected SoundEvent getAmbientSound() {
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
        age++;
        if (time < shootingCooldown) time++;
    }

    public boolean shoot(Player player, BaseATEntity tank, Level world) {
        Player playerEntity = (Player) player;
        ItemStack itemStack = ItemStack.EMPTY;
        BaseATEntity tankEntity = (BaseATEntity) tank;
        for (int i = 0; i < playerEntity.getInventory().getContainerSize(); ++i) {
            ItemStack stack = playerEntity.getInventory().offhand.get(i);
            if (stack.getCount() >= shellsUsed) {
                itemStack = stack;
                break;
            }
            player.displayClientMessage(Component.literal("You don't have any ammo!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
        }
        if (time < shootingCooldown) {
            player.displayClientMessage(Component.literal("Please wait " + (shootingCooldown - time) / 20 + " s !").withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return false;
        }
        if (itemStack.is(TrajansCoreItems.STANDARD_SHELL.get()) && canUseStandard) {
            StandardShell shellEntity = new StandardShell(tankEntity, world);
            shellEntity.shootFromRotation(tankEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.STANDARD_SHELL.get()) && !canUseStandard) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return false;
        }

        if (itemStack.is(TrajansCoreItems.ARMOR_PIERCING_SHELL.get()) && canUseArmorPiercing) {
            ArmorPiercingShell shellEntity = new ArmorPiercingShell(tankEntity, world);
            shellEntity.shootFromRotation(tankEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.ARMOR_PIERCING_SHELL.get()) && !canUseArmorPiercing) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return false;
        }

        if (itemStack.is(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get()) && canUseHighExplosive) {
            HighExplosiveShell shellEntity = new HighExplosiveShell(tankEntity, world);
            shellEntity.shootFromRotation(tankEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get()) && !canUseHighExplosive) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return false;
        }

        if (itemStack.is(TrajansCoreItems.HEAT_SHELL.get()) && canUseHeat) {
            HeatShell shellEntity = new HeatShell(tankEntity, world);
            shellEntity.shootFromRotation(tankEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.HEAT_SHELL.get()) && !canUseHeat) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return false;
        }

        if (itemStack.is(TrajansCoreItems.APCR_SHELL.get()) && canUseAPCR) {
            APCRShell shellEntity = new APCRShell(tankEntity, world);
            shellEntity.shootFromRotation(tankEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.APCR_SHELL.get()) && !canUseAPCR) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return false;
        }
        time = 0;
        return true;
    }
    public boolean canBeAffected(@NotNull MobEffectInstance pPotioneffect) {
        return false;
    }

    public boolean isMoving() {
        return this.onGround && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    @Override
    public boolean fireImmune() {
        if (TrajansCoreConfig.tanksImmuneToFire.get()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        Entity entity = pSource.getDirectEntity();
        if (!TrajansCoreConfig.arrowsDamageTanks.get()) {
            if (entity instanceof AbstractArrow) {
                return false;
            }
        }
        if (pSource == DamageSource.DROWN) {
            return false;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isAlive()) {
            if (this.isVehicle()) {
                if (level.isClientSide) {
                    LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
                    this.setYRot(livingentity.getYRot());
                    this.yRotO = this.getYRot();
                    this.setXRot(livingentity.getXRot() * 0.5F);
                    this.setRot(this.getYRot(), this.getXRot());
                    this.yBodyRot = this.getYRot();
                    this.yHeadRot = this.yBodyRot;
                }
            }
            super.travel(pTravelVector);
        }
    }
    @Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }
}
