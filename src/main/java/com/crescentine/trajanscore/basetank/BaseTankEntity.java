package com.crescentine.trajanscore.basetank;

import com.crescentine.trajanscore.TankShootEvent;
import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.packet.InputPacket;
import com.crescentine.trajanscore.packet.TrajansCoreNetwork;
import com.crescentine.trajanscore.sound.SoundRegistry;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShell;
import com.crescentine.trajanscore.tankshells.low_caliber.LowCaliberShell;
import com.crescentine.trajanscore.tankshells.standard.StandardShell;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
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
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class BaseTankEntity extends AnimatedTankEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public int health = 0;

    public double healAmount = 0;
    public boolean armored;
    public double speedMultiplier = 0;
    public Item tankItem;
    protected double shootingYOffset = 0; // Default Y-offset for shell spawning

    private static final EntityDataAccessor<Integer> FUEL_AMOUNT = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Integer> MAX_HEALTH = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> HEALTH = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> VISIBLE = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.BOOLEAN);

    public float shootingCooldown = 60;

    private float previousRotationYaw;

    public static final EntityDataAccessor<Boolean> DATA_IS_MOVING = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> DATA_IS_ROTATING = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.BOOLEAN);


    public boolean isZoom = false;

    protected SimpleContainer inventory;

    public float time;
    public double armor = 0;
    static int shellsUsed = 1;

    public boolean isTD;
    public boolean isOpposite;
    // ^ for tanks like archer (sigh)

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
    public static final Ingredient AMMO = Ingredient.of(TrajansCoreItems.APCR_SHELL.get(), TrajansCoreItems.ARMOR_PIERCING_SHELL.get(), TrajansCoreItems.HEAT_SHELL.get(), TrajansCoreItems.STANDARD_SHELL.get(), TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get(), TrajansCoreItems.LOW_CALIBER_SHELL.get());
    public boolean showFuel;

    public boolean canUseAPCR;
    public boolean canUseStandard;
    public boolean canUseArmorPiercing;
    public boolean canUseHeat;
    public boolean canUseHighExplosive;

    public boolean canUseLowCaliberShell;

    public int accelerationTime;
    private float lastPlayerYHeadRot;

    private boolean breakableBlocks;
    public int timeInVehicle;
    public double blocksPerSecond = 0.00;

    public float yrot;

    private boolean inputLeft;
    private boolean inputRight;
    private boolean inputUp;
    private boolean inputDown;

    public BaseTankEntity(EntityType<? extends BaseTankEntity> entityType, Level world) {

        super(entityType, world);

    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        //this.setHealth(health);
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 hitPos, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.getHealth() < health) {
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
        if (canAddPassenger(player) && !player.isCrouching()) {
            player.startRiding(this, true);
            return InteractionResult.FAIL;
        }
        return InteractionResult.FAIL;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
        super.checkFallDamage(pY, pOnGround, pState, pPos);
    }

    protected Item getItem() {
        return tankItem;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = getItem().getDefaultInstance();
        CompoundTag compound = new CompoundTag();
        addAdditionalSaveData(compound);
        compound.putInt("health", 1);
        itemStack.addTagElement("EntityTag", compound);
        return itemStack;
    }
    protected void dropItem() {
        ItemStack itemStack = getItemStack();
        spawnAtLocation(itemStack);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Vec3 vec3 = getCollisionHorizontalEscapeVector((double)(this.getBbWidth() * Mth.SQRT_OF_TWO), (double)pLivingEntity.getBbWidth(), pLivingEntity.getYRot());
        double d0 = this.getX() + vec3.x;
        double d1 = this.getZ() + vec3.z;
        BlockPos blockpos = BlockPos.containing(d0, this.getBoundingBox().maxY, d1);
        BlockPos blockpos1 = blockpos.below();
        if (!this.level().isWaterAt(blockpos1)) {
            List<Vec3> list = Lists.newArrayList();
            double d2 = this.level().getBlockFloorHeight(blockpos);
            if (DismountHelper.isBlockFloorValid(d2)) {
                list.add(new Vec3(d0, (double)blockpos.getY() + d2, d1));
            }

            double d3 = this.level().getBlockFloorHeight(blockpos1);
            if (DismountHelper.isBlockFloorValid(d3)) {
                list.add(new Vec3(d0, (double)blockpos1.getY() + d3, d1));
            }

            for(Pose pose : pLivingEntity.getDismountPoses()) {
                for(Vec3 vec31 : list) {
                    if (DismountHelper.canDismountTo(this.level(), vec31, pLivingEntity, pose)) {
                        pLivingEntity.setPose(pose);
                        return vec31;
                    }
                }
            }
        }

        return super.getDismountLocationForPassenger(pLivingEntity);
    }

    @Override
    protected void addPassenger(Entity pPassenger) {
        super.addPassenger(pPassenger);
        if (this.isControlledByLocalInstance() && this.steps > 0) {
            this.steps = 0;
            this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float)this.lerpYRot, (float)this.lerpXRot);
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
        if (DATA_IS_MOVING.equals(pKey)) {
            this.entityData.set(DATA_IS_MOVING, this.isMoving());
        }
        if (DATA_IS_ROTATING.equals(pKey)) {
            this.entityData.set(DATA_IS_ROTATING, this.isRotating());
        }
    }

    public void healTank(double healAmount) {
        if (this.health - this.getHealth() > healAmount) {
            this.setHealth((int) ((float) this.getHealth() + (float) healAmount));
            this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        } else {
            if (this.getHealth() < this.health) {
                this.setHealth(this.health);
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
    public int getMaxFallDistance() {
        return 30;
    }

    /*
    Added in the next beta
    @Override
    public void push(Entity pEntity) {
        super.push(pEntity);
        LivingEntity entity = (LivingEntity) pEntity;
        Entity rider = this.getControllingPassenger();

        if (this.hasControllingPassenger() && pEntity != rider) {
            Player playerRider = (Player) rider;
            assert playerRider != null;
            System.out.println(playerRider.getSpeed());
            entity.hurt(this.damageSources().mobAttack(this), 20);
        }
    }
    */

    public void sendClientInputInfo(boolean up, boolean down, boolean left, boolean right) {
        if (!(
                up == this.inputUp &&
                        down == this.inputDown &&
                        left == this.inputLeft &&
                        right == this.inputRight
        )) {
            setInput(up, down, left, right);
            TrajansCoreNetwork.TANK.sendToServer(new InputPacket(this, inputUp, inputDown, inputLeft, inputRight));
        }
    }

    @Override
    public void push(Entity pEntity) {
        super.push(pEntity);
        LivingEntity entity = (LivingEntity) pEntity;
        Entity rider = this.getControllingPassenger();

        if (this.hasControllingPassenger() && pEntity != rider) {
            Player playerRider = (Player) rider;
            assert playerRider != null;
            if (this.getControllingPassenger() instanceof Player) {
                entity.hurt(this.damageSources().playerAttack((Player) this.getControllingPassenger()), (float) this.accelerationTime /10);
            }
        }
    }

    public boolean isBreakableBlock(BlockState blockstate) {
        return blockstate.is(BlockTags.REPLACEABLE) || blockstate.is(BlockTags.LEAVES) || blockstate.is(BlockTags.FLOWERS) || blockstate.is(BlockTags.ICE);
    }

    private boolean destroyBlocks(AABB pArea) {
        int i = Mth.floor(pArea.minX);
        int j = Mth.floor(pArea.minY);
        int k = Mth.floor(pArea.minZ);
        int l = Mth.floor(pArea.maxX);
        int i1 = Mth.floor(pArea.maxY);
        int j1 = Mth.floor(pArea.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int k1 = i; k1 <= l; ++k1) {
            for (int l1 = j; l1 <= i1; ++l1) {
                for (int i2 = k; i2 <= j1; ++i2) {
                    BlockPos blockpos = new BlockPos(k1, l1, i2);
                    BlockState blockstate = this.level().getBlockState(blockpos);
                    if (!blockstate.isAir() && isBreakableBlock(blockstate)) {
                        if (isBreakableBlock(blockstate)) {
                            flag1 = this.level().removeBlock(blockpos, false) || flag1;
                        } else {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1) {
            BlockPos blockpos1 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
        }

        return flag;
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
    protected void positionRider(Entity pPassenger, MoveFunction pCallback) {
        //pPassenger.setYRot(pPassenger.getYHeadRot());
        super.positionRider(pPassenger, pCallback);
    }




    public void setInput(boolean pInputLeft, boolean pInputRight, boolean pInputUp, boolean pInputDown) {
        this.inputLeft = pInputLeft;
        this.inputRight = pInputRight;
        this.inputUp = pInputUp;
        this.inputDown = pInputDown;
    }
    
    private void controlTank() {
        if (this.isVehicle() && this.hasControllingPassenger() && this.getFuelAmount() > 0) {
            double gravity = 0.875D;
            double upwardForce = 0.5f;

            if (!this.onGround()) {
                Vec3 motion = this.getDeltaMovement();
                this.setDeltaMovement(motion.x, motion.y - gravity, motion.z);
            }

            LivingEntity livingEntity = this.getControllingPassenger();

            if (this.getControllingPassenger() == livingEntity) {
                double moveX = 0.0;
                double moveY = 0.0;
                double moveZ = 0.0;

                if (this.hasControllingPassenger()) {
                    if (this.inputLeft) {
                        this.setYRot((float) (this.getYRot() - 1.0F - speedMultiplier));
                    }

                    if (this.inputRight) {
                        this.setYRot((float) (this.getYRot() + 1.0F + speedMultiplier));
                    }

                    double yawRad = Math.toRadians(this.getYRot());
                    double x = -Math.sin(yawRad);
                    double z = Math.cos(yawRad);

                    boolean shouldMoveForward = isOpposite ? this.inputDown : this.inputUp;
                    boolean shouldMoveBackward = isOpposite ? this.inputUp : this.inputDown;

                    if (shouldMoveForward) {
                        moveX += x * 0.1 * accelerationTime / 10 * speedMultiplier;
                        moveZ += z * 0.1 * accelerationTime / 10 * speedMultiplier;
                    }
                    if (shouldMoveBackward) {
                        moveX -= x * 0.1 * 2 * speedMultiplier; // backward speed
                        moveZ -= z * 0.1 * 2 * speedMultiplier;
                    }

                    Vec3 deltaMovement = new Vec3(moveX, moveY, moveZ);
                    Vec3 currentDeltaMovement = this.getDeltaMovement();
                    Vec3 updatedDeltaMovement = currentDeltaMovement.add(deltaMovement);

                    if (!this.horizontalCollision) {
                        this.setDeltaMovement(updatedDeltaMovement);
                    } else {
                        if (this.onGround()) {
                            this.setDeltaMovement(updatedDeltaMovement.add(0.0, upwardForce, 0.0));
                        }
                    }
                }
            } else {
                this.setYRot(this.getYRot());
            }
        }
    }


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

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return isAlive();
    }

    public void tickLerp() {
        if (this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;
            syncPacketPositionCodec(getX(), getY(), getZ());
        } else if (lerpSteps > 0) {
            this.setPos(
                    this.getX() + ((this.clientX - this.getX()) / (double)this.lerpSteps),
                    this.getY() + ((this.clientY - this.getY()) / (double)this.lerpSteps),
                    this.getZ() + ((this.clientZ - this.getZ()) / (double)this.lerpSteps)
            );
            this.setYRot((float) (this.getYRot() + (Mth.wrapDegrees(this.clientYaw - this.getYRot()) / (float)this.lerpSteps)));

            this.lerpSteps--;
        }
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;
        this.clientYaw = yaw;
        this.clientPitch = pitch;
        this.steps = this.getType().updateInterval() + 1;
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
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void tick() {
        //ystem.out.println(this.getHealth() + "/" + this.health);
        if (!this.isMoving()) {
            entityData.set(DATA_IS_MOVING, isMoving());
        }
        if (!this.isRotating()) {
            entityData.set(DATA_IS_ROTATING, isRotating());
        }

        // System.out.println(this.isMovingForward());

        if (!level().isClientSide) {
            this.xo = getX();
            this.yo = getY();
            this.zo = getZ();
        }
        this.setAnimData(DataTickets.ACTIVE, true);
        super.tick();
        tickLerp();

        // unconditional, not sure why this works
        this.setDeltaMovement(Vec3.ZERO);
        this.yrot = this.getYRot();

        // TODO: Revisit this temporary way of checking if it is moving
        if (!this.getDeltaMovement().equals(Vec3.ZERO) || !this.position().equals(this.previousPosition)) {
            this.previousPosition = this.position();
        }

        if (this.getYRot() != this.previousRotationYaw) {
            this.previousRotationYaw = this.getYRot();
        }

        double gravity = 0.875D;
        if (!this.onGround()) {
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x, motion.y - gravity, motion.z);
        }

        if (this.isControlledByLocalInstance()) {
            if (this.level().isClientSide) {
                controlTank();
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        fuelTick();
        accelerationTick();
        age++;

        if (time < shootingCooldown) {
            time++;
            float progress = (shootingCooldown - time) / 20;
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
//        if (this.xo == this.getX() || this.zo == this.getZ()) {
//            this.playSound(SoundRegistry.TANK_IDLE.get(), 1.0f, 1.0f);
//
//        }
        }

    protected void fuelTick() {
        int fuel = getFuelAmount();
        if ((this.isMoving() || this.isRotating()) && this.isVehicle() && fuel > 0) {
            removeFuel(1);
        }
    }

    public boolean isRotating() {
        return this.getYRot() != this.previousRotationYaw;
    }

    protected void accelerationTick() {
        if (this.isMovingForward() && this.isVehicle() && accelerationTime <= 160) {
            accelerationTime++;
        }

        if (accelerationTime >= 0) {
            // Decrease AccTime when not moving OR not in tank OR no fuel
            if (!this.isVehicle() || !this.isMovingForward() || getFuelAmount() == 0) {
                accelerationTime -= 20;
                if (accelerationTime < 0) {
                    accelerationTime = 1;
                }
            }
            if (this.isMovingBackward()) {
                // Implicit cast from 'float' to 'int' in compound assignment can be lossy
                accelerationTime +=0.5f;
            }
        }
            // System.out.println("e" + accelerationTime);

        age++;
        if (level().isClientSide() && this.isVehicle() && this.age % 10 == 0 && getFuelAmount() > 0) {
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ(), d0, d1, d2);
            this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + 1.0D, this.getY() + 1.0D, this.getZ(), d0, d1, d2);
        }

       if (!this.level().isClientSide) {
            this.breakableBlocks = this.destroyBlocks(this.getBoundingBox());
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

    public boolean isMovingForward() {
        return isOpposite ? this.inputDown : this.inputUp;
    }

    public boolean isMovingBackward() {
        return isOpposite ? this.inputUp : this.inputDown;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(FUEL_AMOUNT, 0);
        entityData.define(SPEED, 0.0f);
        entityData.define(HEALTH, health);
        entityData.define(MAX_HEALTH, health);
        entityData.define(VISIBLE, false);
        entityData.define(DATA_IS_MOVING, this.isMoving());
        entityData.define(DATA_IS_ROTATING, this.isRotating());
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



    public void setVisibility(boolean v) {
        entityData.set(VISIBLE, v);
    }

    public boolean getVisibility() {
        return entityData.get(VISIBLE);
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
        if (pCompound.contains("max_health")) {
            int maxHealth = pCompound.getInt("max_health");
//            if (maxHealth <= 0) {
//                maxHealIth = 20;
//            }
            entityData.set(MAX_HEALTH, maxHealth);
        }

        if (pCompound.contains("health")) {
            int health = pCompound.getInt("health");
            entityData.set(HEALTH, health);
        }
        if (pCompound.contains("visible")) {
            boolean visible = pCompound.getBoolean("visible");
            entityData.set(VISIBLE, visible);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("fuel", getFuelAmount());
        pCompound.putInt("health", entityData.get(HEALTH));
        pCompound.putInt("max_health", entityData.get(MAX_HEALTH));
        pCompound.putBoolean("visible", entityData.get(VISIBLE));
    }







    public void shoot(Player player, BaseTankEntity tank, Level world) {
        TankShootEvent tankEvent = new TankShootEvent(this);
        Player playerEntity = (Player) player;
        ItemStack itemStack = ItemStack.EMPTY;
        BaseTankEntity tankEntity = (BaseTankEntity) tank;

        if (!tankEvent.isCanceled() && getFuelAmount() > 0) {
            boolean foundAmmo = false;

            for (int i = 0; i < playerEntity.getInventory().getContainerSize(); ++i) {
                ItemStack stack = playerEntity.getInventory().offhand.get(i);

                if (!stack.isEmpty() && stack.getCount() >= shellsUsed && AMMO.test(stack)) {
                    itemStack = stack;
                    foundAmmo = true;
                    break;
                }
            }

            float l = shootingCooldown - time;
            if (foundAmmo && !(l > 0)) {
                triggerAnim("shoot_controller", "shoot");
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
            float progress = (shootingCooldown - time) / 20;
            player.displayClientMessage(Component.literal("Please wait " + progress + "s !").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD), false);
            player.level().playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }
        if (getFuelAmount() < 1 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("You don't have any fuel!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }
        if (!AMMO.test(itemStack)) {
            player.displayClientMessage(Component.literal("You don't have any ammo!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }

        float yRot = tankEntity.isTD ? tankEntity.getYRot() : playerEntity.getYRot();
        if (isOpposite) {
            yRot += 180.0F;
        }

        if (itemStack.is(TrajansCoreItems.STANDARD_SHELL.get()) && canUseStandard) {
            StandardShell shellEntity = new StandardShell(tankEntity.getControllingPassenger(), world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            Vec3 shootPos = tankEntity.position().add(0, shootingYOffset, 0);
            shellEntity.setPos(shootPos);
            shellEntity.shootFromRotation(tankEntity, xRot, yRot, 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new TankShootEvent(this));
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.STANDARD_SHELL.get()) && !canUseStandard) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }

        if (itemStack.is(TrajansCoreItems.ARMOR_PIERCING_SHELL.get()) && canUseArmorPiercing) {
            ArmorPiercingShell shellEntity = new ArmorPiercingShell(tankEntity.getControllingPassenger(), world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            Vec3 shootPos = tankEntity.position().add(0, shootingYOffset, 0);
            shellEntity.setPos(shootPos);
            shellEntity.shootFromRotation(tankEntity, xRot, yRot, 0.0F, 3.5F, 1.0F);
            MinecraftForge.EVENT_BUS.post(new TankShootEvent(this));
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.ARMOR_PIERCING_SHELL.get()) && !canUseArmorPiercing) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }

        if (itemStack.is(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get()) && canUseHighExplosive) {
            HighExplosiveShell shellEntity = new HighExplosiveShell(tankEntity.getControllingPassenger(), world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            Vec3 shootPos = tankEntity.position().add(0, shootingYOffset, 0);
            shellEntity.setPos(shootPos);
            shellEntity.shootFromRotation(tankEntity, xRot, yRot, 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new TankShootEvent(this));
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get()) && !canUseHighExplosive) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }

        if (itemStack.is(TrajansCoreItems.HEAT_SHELL.get()) && canUseHeat) {
            HeatShell shellEntity = new HeatShell(tankEntity.getControllingPassenger(), world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            Vec3 shootPos = tankEntity.position().add(0, shootingYOffset, 0);
            shellEntity.setPos(shootPos);
            shellEntity.shootFromRotation(tankEntity, xRot, yRot, 0.0F, 2.0F, 0F);
            MinecraftForge.EVENT_BUS.post(new TankShootEvent(this));
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.HEAT_SHELL.get()) && !canUseHeat) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }

        if (itemStack.is(TrajansCoreItems.APCR_SHELL.get()) && canUseAPCR) {
            APCRShell shellEntity = new APCRShell(tankEntity.getControllingPassenger(), world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            Vec3 shootPos = tankEntity.position().add(0, shootingYOffset, 0);
            shellEntity.setPos(shootPos);
            shellEntity.shootFromRotation(tankEntity, xRot, yRot, 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new TankShootEvent(this));
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }

        if (itemStack.is(TrajansCoreItems.APCR_SHELL.get()) && !canUseAPCR) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }
        if (itemStack.is(TrajansCoreItems.LOW_CALIBER_SHELL.get()) && canUseLowCaliberShell) {
            LowCaliberShell shellEntity = new LowCaliberShell(tankEntity.getControllingPassenger(), world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            Vec3 shootPos = tankEntity.position().add(0, shootingYOffset, 0);
            shellEntity.setPos(shootPos);
            shellEntity.shootFromRotation(tankEntity, xRot, yRot, 0.0F, 3.5F, 0F);
            MinecraftForge.EVENT_BUS.post(new TankShootEvent(this));
            world.addFreshEntity(shellEntity);
            itemStack.shrink(shellsUsed);
        }
        if (itemStack.is(TrajansCoreItems.LOW_CALIBER_SHELL.get()) && !canUseLowCaliberShell) {
            player.displayClientMessage(Component.literal("Shell Type disabled with this vehicle!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            world.playSound(null, player.blockPosition(), SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0f, 1.0f);
            return;
        }
        time = 0;
    }


    public void fuelLeft(Player player) {
        double fuel = getFuelAmount();
        if (fuel < 1200 && fuel > 1 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("Low fuel! Amount of time before fuel runs out " + fuel / 20 + " seconds or " + String.format("%.2f", fuel / 1200) + " minutes ").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return;
        }
        if (fuel > 1200 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("The amount of fuel remaining: " + fuel / 20 + " seconds, or " + String.format("%.2f", fuel / 1200) + " minutes ").withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD), false);
            return;
        }
        if (getFuelAmount() < 1 && TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("No fuel remaining!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
            return;
        }
        if (!TrajansCoreConfig.fuelSystemEnabled.get()) {
            player.displayClientMessage(Component.literal("Fuel is not enabled! Enable fuel in the config, or contact your server administrator.").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), false);
        }
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(SoundRegistry.TANK_MOVE.get(), 0.2f, 1);
    }


    @Override
    public float getStepHeight() {
        return 1.0f;
    }

    public boolean canBeAffected(@NotNull MobEffectInstance pPotioneffect) {
        return false;
    }

    public boolean isMoving() {
        return !this.position().equals(this.previousPosition);
    }

    @Override
    public boolean fireImmune() {
        return TrajansCoreConfig.tanksImmuneToFire.get();
    }

    protected void clampRotation(Entity pEntityToUpdate) {
        pEntityToUpdate.setYBodyRot(this.getYRot());
        float f = Mth.wrapDegrees(pEntityToUpdate.getYRot() - this.getYRot());
        float f1 = Mth.clamp(f, -130.0F, 130.0F);
        pEntityToUpdate.yRotO += f1 - f;
        pEntityToUpdate.setYRot(pEntityToUpdate.getYRot() + f1 - f);
        pEntityToUpdate.setYHeadRot(pEntityToUpdate.getYRot());
    }


    //broken
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        /*Entity entity = pSource.getDirectEntity();

        pAmount = 3;

        if (pSource.type() == level().damageSources().cactus().type() || pSource.type() == level().damageSources().sweetBerryBush().type()) {
            return false;
        }

            if (!TrajansCoreConfig.arrowsDamageTanks.get()) {
            if (entity instanceof AbstractArrow) {
                return false;
            }
        }
        /*
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
        return super.hurt(pSource, pAmount);
         */
        if (pSource.type() == level().damageSources().cactus().type() || pSource.type() == level().damageSources().sweetBerryBush().type()) {
            return false;
        }

        if (pSource.getEntity() == this) {
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
        if (level().isClientSide || isRemoved()) {
            return false;
        }
        if (health < 0) {
            return false;
        }

        this.setHealth((int) (this.entityData.get(HEALTH)-pAmount));


        if (this.getHealth() <= 0) {
            this.kill();
            dropItem();
        }

        return true;
    }

    public double getOverlaySpeed() {
        return blocksPerSecond;
    }

    @Override
    protected void removePassenger(Entity pPassenger) {
        if (pPassenger instanceof Player) {
            Player player = (Player) pPassenger;
            lastPlayerYHeadRot = player.getYHeadRot();
        }
        this.timeInVehicle = 0;
        super.removePassenger(pPassenger);
    }

    public float getLastPlayerYHeadRot() {
        return lastPlayerYHeadRot;
    }

    public void setLastPlayerYHeadRot(float lastPlayerYHeadRot) {
        this.lastPlayerYHeadRot = lastPlayerYHeadRot;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
        //        .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));
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
        super.stopRiding();
    }


}
