package com.crescentine.trajanscore.basetank;

import com.crescentine.trajanscore.ATShootEvent;
import com.crescentine.trajanscore.TankModClient;
import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.sound.SoundRegistry;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import com.crescentine.trajanscore.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShell;
import com.crescentine.trajanscore.tankshells.standard.StandardShell;
import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;

import static com.crescentine.trajanscore.basetank.BaseTankEntity.AMMO;

public class BaseATEntity extends AnimatedTankEntity {
    protected BaseATEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }




    /*public boolean canUseAPCR;
    public boolean canUseStandard;
    public boolean canUseArmorPiercing;
    public boolean canUseHeat;
    public boolean canUseHighExplosive;
    public int shootingCooldown = 180;
    private ImmutableList<Entity> passengers = ImmutableList.of();

    public int time;
    public double armor = 0;
    static int shellsUsed = 1;
    public double health = 0;
    public double speedMultiplier = 0;


    public BaseATEntity(EntityType<?> entityType, Level world) {
        super((EntityType<? extends LivingEntity>) entityType, world);
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
        Minecraft mc = Minecraft.getInstance();

        player.displayClientMessage(Component.literal("Press " + mc.options.keyShift.getKey().getDisplayName().getString() + " to dismount"), false);
        return InteractionResult.FAIL;
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return this.passengers.isEmpty();
    }


    @Override
    public boolean shouldRiderSit() {
        return true;
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
    protected void removePassenger(Entity pPassenger) {
        super.removePassenger(pPassenger);
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


        if (time < shootingCooldown) time++;
    }

    public boolean shoot(Player player, BaseATEntity tank, Level world) {
        ATShootEvent tankEvent = new ATShootEvent(this);
        Player playerEntity = (Player) player;

        ItemStack itemStack = ItemStack.EMPTY;
        BaseATEntity tankEntity = (BaseATEntity) tank;


        if (!tankEvent.isCanceled()) {
            boolean foundAmmo = false;

            for (int i = 0; i < playerEntity.getInventory().getContainerSize(); ++i) {
                ItemStack stack = playerEntity.getInventory().offhand.get(i);

                if (!stack.isEmpty() && stack.getCount() >= shellsUsed && AMMO.test(stack)) {
                    itemStack = stack;
                    foundAmmo = true;
                    break;
                }
            }
            int l = shootingCooldown-time;
            if (foundAmmo && !(l>0)) {
                triggerAnim("shoot_controller", "shoot");
                this.level().playSeededSound(null, this.getX(), this.getY(), this.getZ(), SoundRegistry.AT_SHOOT.get(), SoundSource.HOSTILE, 1f, 1f, 0);

            }
        }



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
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new ATShootEvent(this));
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
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new ATShootEvent(this));
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
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new ATShootEvent(this));
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
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new ATShootEvent(this));
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
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new ATShootEvent(this));
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
        return this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
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


        if (pSource.type() == level().damageSources().cactus().type()) {
            return false;
        }

        if (!TrajansCoreConfig.arrowsDamageTanks.get()) {
            if (entity instanceof AbstractArrow) {
                return false;
            }
        }

        if (!(entity instanceof BaseShell)) {
            if (!TrajansCoreConfig.meleeDamageTanks.get() && entity instanceof Player || !TrajansCoreConfig.meleeDamageTanks.get() && entity instanceof Mob) {
                if (pSource.type() == level().damageSources().mobAttack((LivingEntity) entity).type()) {
                    return false;
                }
                if (pSource.type() == level().damageSources().playerAttack((Player) entity).type()) {
                    return false;

                }
            }
        }

        if (pSource == level().damageSources().drown()) {
            return false;
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));

    }


    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isAlive() && TrajansCoreConfig.ATMountMove.get()) {
            if (this.isVehicle()) {
                LivingEntity livingEntity = (LivingEntity) this.getControllingPassenger();

                float forward = livingEntity.zza; // Consider only forward/backward movement
                if (forward <= 0.0F) {
                    forward *= 0.25F;
                }

                this.setSpeed(0.045f);

                super.travel(new Vec3(0, pTravelVector.y, forward));
            }
        }
    }


    @Override
    protected void tickRidden(Player pPlayer, Vec3 pos) {
        super.tickRidden(pPlayer, pos);
        this.setRot(pPlayer.getYRot(), pPlayer.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : (LivingEntity) this.getPassengers().get(0);
    }

     */
}
