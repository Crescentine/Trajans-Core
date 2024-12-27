package com.crescentine.trajanscore.basetank;

import com.crescentine.trajanscore.ATShootEvent;
import com.crescentine.trajanscore.TankShootEvent;
import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.sound.SoundRegistry;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import com.crescentine.trajanscore.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShell;
import com.crescentine.trajanscore.tankshells.low_caliber.LowCaliberShell;
import com.crescentine.trajanscore.tankshells.standard.StandardShell;
import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import static com.crescentine.trajanscore.basetank.BaseTankEntity.AMMO;

public class BaseATEntity extends AnimatedTankEntity {



    private float lastYRot;

    private boolean inputUp;
    private boolean inputDown;


    public static final EntityDataAccessor<Integer> MAX_HEALTH = SynchedEntityData.defineId(BaseATEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HEALTH = SynchedEntityData.defineId(BaseATEntity.class, EntityDataSerializers.INT);



    public boolean canUseAPCR;
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


    public BaseATEntity(EntityType<? extends BaseATEntity> entityType, Level world) {
        super(entityType, world);
    }


    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!player.isShiftKeyDown()) {
            if (player.getVehicle() != this) {
                if (!level().isClientSide) {
                    player.startRiding(this);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }


    @Override
    public void stopRiding() {
        super.stopRiding();
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return this.passengers.isEmpty();
    }


    @Override
    protected void defineSynchedData() {

        super.defineSynchedData();
        entityData.define(HEALTH, (int) health);
        entityData.define(MAX_HEALTH, (int) health);

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("max_health")) {
            int maxHealth = pCompound.getInt("max_health");
            if (maxHealth <= 0) {
                maxHealth = 20;
            }
            entityData.set(MAX_HEALTH, maxHealth);
        }

        if (pCompound.contains("health")) {
            int health = pCompound.getInt("health");
            entityData.set(HEALTH, health);
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("health", entityData.get(HEALTH));
        pCompound.putInt("max_health", entityData.get(MAX_HEALTH));
    }

    public void setHealth(int health) {
        entityData.set(HEALTH, Math.max(health, 0));
    }

    public int getHealth() {
        return entityData.get(HEALTH);
    }

    public int getMaxHealth() {
        return entityData.get(MAX_HEALTH);
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
    protected void removePassenger(Entity pPassenger) {
        lastYRot = this.getYRot();
        super.removePassenger(pPassenger);
    }


    public float getLastYRot() {
        return lastYRot;
    }


    @Override
    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.PLAYER_SPLASH;
    }



    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.GENERIC_SWIM;
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
            StandardShell shellEntity = new StandardShell(this.getControllingPassenger(), world);
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
            ArmorPiercingShell shellEntity = new ArmorPiercingShell(this.getControllingPassenger(), world);
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
            HighExplosiveShell shellEntity = new HighExplosiveShell(this.getControllingPassenger(), world);
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
            HeatShell shellEntity = new HeatShell(this.getControllingPassenger(), world);
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
            APCRShell shellEntity = new APCRShell(this.getControllingPassenger(), world);
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
        if (pSource.type() == level().damageSources().cactus().type() || pSource.type() == level().damageSources().sweetBerryBush().type()) {
            return false;
        }

        Entity entity = pSource.getDirectEntity();
        if (!TrajansCoreConfig.arrowsDamageTanks.get()) {
            if (entity instanceof AbstractArrow) {
                return false;
            }
        }
        if (!TrajansCoreConfig.meleeDamageTanks.get() && entity instanceof Player) {
            if (pSource.type() == level().damageSources().playerAttack((Player) entity).type()) {
                return false;

            }
        }

        if (!TrajansCoreConfig.meleeDamageTanks.get() && entity instanceof Mob) {
            if (pSource.type() == level().damageSources().mobAttack((LivingEntity) entity).type()) {
                return false;
            }
        }

        if (pSource == level().damageSources().drown()) {
            return false;
        }

        this.setHealth((int) (this.entityData.get(HEALTH)-pAmount));
        if(getHealth()<=0) {
            kill();
        }


        return super.hurt(pSource, pAmount);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));

    }


    public void setInput(boolean pInputUp, boolean pInputDown) {
        this.inputUp = pInputUp;
        this.inputDown = pInputDown;
    }





    private void controlAT() {
        if (this.isVehicle() && this.hasControllingPassenger()) {
            if (!this.onGround()) {
                double gravity = 0.08D;
                Vec3 motion = this.getDeltaMovement();
                this.setDeltaMovement(motion.x, motion.y - gravity, motion.z);
            }

            LivingEntity livingEntity = this.getControllingPassenger();

            if (this.getControllingPassenger() == livingEntity) {
                double moveX = 0.0;
                double moveY = 0.0;
                double moveZ = 0.0;
                float forwardSpeed = 0.1f;
                float backwardSpeed = 0.05f;

                if (this.hasControllingPassenger()) {
                    this.setYRot(livingEntity.getYHeadRot());

                    double yawRad = Math.toRadians(this.getYRot());
                    double x = -Math.sin(yawRad);
                    double z = Math.cos(yawRad);




                    if (TrajansCoreConfig.ATMountMove.get()) {

                        if (this.inputUp) {
                            moveX += x * forwardSpeed;
                            moveZ += z * forwardSpeed;
                        }

                        if (this.inputDown) {
                            moveX -= x * backwardSpeed;
                            moveZ -= z * backwardSpeed;
                        }

                        this.move(MoverType.SELF, new Vec3(moveX, moveY, moveZ));

                        if (this.horizontalCollision && this.onGround() && this.hasControllingPassenger()) {
                            float upwardForce = 0.5f;
                            this.setDeltaMovement(this.getDeltaMovement().add(0.0, upwardForce, 0.0));
                        }
                    }
                }
            } else {
                this.setYRot(this.getYRot());
            }
        }
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : (LivingEntity) this.getPassengers().get(0);
    }


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);





    private float previousRotationYaw;



    private boolean shooting = false;

    public boolean isVisiblePlayer = true;
    public boolean isZoom = false;


    public boolean isCamoRed = false;
    protected SimpleContainer inventory;



    public int age;


    double d0 = this.random.nextGaussian() * 0.03D;
    public static final Ingredient AMMO = Ingredient.of(TrajansCoreItems.APCR_SHELL.get(), TrajansCoreItems.ARMOR_PIERCING_SHELL.get(), TrajansCoreItems.HEAT_SHELL.get(), TrajansCoreItems.STANDARD_SHELL.get(), TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get(), TrajansCoreItems.LOW_CALIBER_SHELL.get());




    public int timeInVehicle;
    public double blocksPerSecond = 0.00;

    public float yrot;









    @Override
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand hand) {

        if (canAddPassenger(player)) {
            player.startRiding(this, true);
            return InteractionResult.FAIL;
        }
        return InteractionResult.FAIL;
    }



    public void toggleVisibility() {
        this.isVisiblePlayer = !this.isVisiblePlayer;
    }








    @Override
    public int getMaxFallDistance() {
        return 30;
    }









    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {

    }


    public static float SCALE_FACTOR = 0.7F;

    private int steps;
    private double clientX;
    private double clientY;
    private double clientZ;
    private double clientYaw;
    private double clientPitch;

    protected float deltaRotation;

    private Vec3 previousPosition = Vec3.ZERO;


    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYRot;
    private double lerpXRot;













    protected void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setYBodyRot(getYRot());
        entityToUpdate.setYRot(entityToUpdate.getYRot() + deltaRotation);
        entityToUpdate.setYHeadRot(entityToUpdate.getYRot());
    }




    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }


    @Override
    public boolean isPickable() {
        return isAlive();
    }



    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }



    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(Vec3.ZERO);
        this.yrot = this.getYRot();


        double gravity = 0.875D;
        if (!this.onGround()) {
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x, motion.y - gravity, motion.z);
        }


        // TODO: Revisit this temporary way of checking if it is moving
        if (!this.getDeltaMovement().equals(Vec3.ZERO) || !this.position().equals(this.previousPosition)) {
            this.previousPosition = this.position();
        }

        if (this.getYRot() != this.previousRotationYaw) {
            this.previousRotationYaw = this.getYRot();
        }

        if (this.isControlledByLocalInstance()) {


            controlAT();
            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }
        System.out.println(this.getYRot());


        age++;
        if (time < shootingCooldown) {
            time++;
            float progress = (float) (shootingCooldown - time) / 20;
            float roundedProgress = Math.round(progress * 10.0f) / 10.0f; // Round to 1 decimal place

            if (this.getControllingPassenger() instanceof Player && this.isVehicle() && !this.level().isClientSide()) {
                Player p = (Player) this.getControllingPassenger();

                p.displayClientMessage(Component
                        .literal(roundedProgress + "s")
                        .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD), true);
            }
        }
        if (this.isVehicle()) {
            timeInVehicle++;
        }
        if (timeInVehicle % 20 == 0) {
            blocksPerSecond = this.getDeltaMovement().horizontalDistance() * 20;
        }
    }



    public boolean isRotating() {
        return this.getYRot() != this.previousRotationYaw;
    }

















    @Override
    public float getStepHeight() {
        return 1.0f;
    }










    protected void clampRotation(Entity pEntityToUpdate) {
        pEntityToUpdate.setYBodyRot(this.getYRot());
        float f = Mth.wrapDegrees(pEntityToUpdate.getYRot() - this.getYRot());
        float f1 = Mth.clamp(f, -130.0F, 130.0F);
        pEntityToUpdate.yRotO += f1 - f;
        pEntityToUpdate.setYRot(pEntityToUpdate.getYRot() + f1 - f);
        pEntityToUpdate.setYHeadRot(pEntityToUpdate.getYRot());
    }









    public double getOverlaySpeed() {
        return blocksPerSecond;
    }




    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }




}
