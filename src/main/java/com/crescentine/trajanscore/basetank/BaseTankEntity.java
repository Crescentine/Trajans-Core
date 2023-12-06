package com.crescentine.trajanscore.basetank;

import com.crescentine.trajanscore.TankModClient;
import com.crescentine.trajanscore.TankShootEvent;
import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
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
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
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
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class BaseTankEntity extends AnimatedTankEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final ResourceLocation LOADING_1 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading1.png");
    private static final ResourceLocation LOADING_2 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading2.png");
    private static final ResourceLocation LOADING_3 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading3.png");
    private static final ResourceLocation LOADING_4 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading4.png");
    private static final ResourceLocation LOADING_5 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading5.png");

    private static final ResourceLocation LOADING_6 = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/loading6.png");

    public double healAmount = 0;
    public boolean armored;
    public double speedMultiplier = 0;
    private static final EntityDataAccessor<Integer> FUEL_AMOUNT = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(BaseTankEntity.class, EntityDataSerializers.FLOAT);
    public float shootingCooldown = 60;

    public CoreGeoBone gun;

    private boolean shooting = false;

    public boolean isVisiblePlayer = true;
    public boolean isZoom = false;


    public boolean isCamoRed = false;
    protected SimpleContainer inventory;


    public float time;
    public double armor = 0;
    static int shellsUsed = 1;
    public double health = 0;
    private ResourceLocation lootTable;

    public boolean isTD;

    public int age;

    public double coalFuelAmount = TrajansCoreConfig.coalFuelAmount.get();
    public double lavaFuelAmount = TrajansCoreConfig.lavaFuelAmount.get();
    public double maxFuel = 12000.00;
    public boolean shootingAnimation;
    double d0 = this.random.nextGaussian() * 0.03D;
    double d1 = this.random.nextGaussian() * 0.03D;
    double d2 = this.random.nextGaussian() * 0.03D;
    private static final Ingredient FUELS = Ingredient.of(Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK);
    private static final Ingredient COAL_FUEL = Ingredient.of(Items.COAL, Items.CHARCOAL);
    private static final Ingredient COAL_BLOCK_FUEL = Ingredient.of(Items.COAL_BLOCK);
    private static final Ingredient LAVA_FUEL = Ingredient.of(Items.LAVA_BUCKET);
    private static final Ingredient HEALS = Ingredient.of(Items.IRON_BLOCK, Items.IRON_INGOT);
    private static final Ingredient IRON_INGOT = Ingredient.of(Items.IRON_INGOT);
    private static final Ingredient IRON_BLOCK = Ingredient.of(Items.IRON_BLOCK);
    public static final Ingredient AMMO = Ingredient.of(TrajansCoreItems.APCR_SHELL.get(), TrajansCoreItems.ARMOR_PIERCING_SHELL.get(), TrajansCoreItems.HEAT_SHELL.get(), TrajansCoreItems.STANDARD_SHELL.get(), TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get(), TrajansCoreItems.LOW_CALIBER_SHELL.get());
    private ImmutableList<Entity> passengers = ImmutableList.of();
    public boolean showFuel;
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(AbstractHorse.class, EntityDataSerializers.BYTE);

    public boolean canUseAPCR;
    public boolean canUseStandard;
    public boolean canUseArmorPiercing;
    public boolean canUseHeat;
    public boolean canUseHighExplosive;

    public boolean canUseLowCaliberShell;

    public boolean turretFollow;
    public int accelerationTime;
    private boolean breakableBlocks;
    public int timeInVehicle;
    public double blocksPerSecond = 0.00;


    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(AbstractChestedHorse.class, EntityDataSerializers.BOOLEAN);
    public static final int INV_CHEST_COUNT = 15;





    public BaseTankEntity(EntityType<?> entityType, Level world) {
        super((EntityType<? extends Animal>) entityType, world);
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



    public void toggleVisibility() {
        this.isVisiblePlayer = !this.isVisiblePlayer;
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
        // Stops overfueing
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
                if (livingentity.level().isClientSide()) {
                    if (TankModClient.SYNC_TURRET_WITH_TANK.consumeClick() && level().isClientSide && livingentity.isPassenger()) {
                        turretFollow = !turretFollow;
                    }
                    if (turretFollow) {
                        this.setYRot(livingentity.getYRot());
                        this.yRotO = this.getYRot();
                        this.setXRot(livingentity.getXRot() * 0.5F);
                        this.setRot(this.getYRot(), this.getXRot());
                        this.yBodyRot = this.getYRot();
                        this.yHeadRot = this.yBodyRot;
                    }
                }

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
                        if (net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.level(), blockpos, this) && isBreakableBlock(blockstate)) {
                            flag1 = this.level().removeBlock(blockpos, false) || flag1;
                        } else {
                            flag = true;
                        }
                    }
                }
            }
        }

      /*  if (flag1) {
            BlockPos blockpos1 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
            this.level.levelEvent(2008, blockpos1, 0);
        } */

        return flag;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
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
        Minecraft mc = Minecraft.getInstance();
        if (TankModClient.TOGGLE_ZOOM_KEY.consumeClick() && mc.player.getVehicle() instanceof BaseTankEntity b) {
            b.isZoom = !b.isZoom;
        }
        if (TankModClient.TOGGLE_VISIBILITY_KEY.consumeClick() && mc.player.getVehicle() instanceof BaseTankEntity b) {
            b.isVisiblePlayer = !b.isVisiblePlayer;
        }
        fuelTick();
        accelerationTick();
        age++;
        if (!this.hasControllingPassenger()) {
            this.setSpeed(0f);
        }
        if (time < shootingCooldown) {
            time++;
            float progress = (shootingCooldown - time) / 20;
            float roundedProgress = Math.round(progress * 10.0f) / 10.0f; // Round to 1 decimal place



            if (this.getControllingPassenger() instanceof Player) {
                Player p = (Player) this.getControllingPassenger();
                if(progress == 0) {
                    p.displayClientMessage(Component
                            .literal(shootingCooldown + "s")
                            .withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD), true);


                }

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
            // Decrease AccTime when not moving OR not in tank OR no fuel
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

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean isShooting) {
        shooting = isShooting;
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

            float l = shootingCooldown-time;
            if (foundAmmo && !(l>0)) {
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

        if (itemStack.is(TrajansCoreItems.STANDARD_SHELL.get()) && canUseStandard) {
            StandardShell shellEntity = new StandardShell(tankEntity, world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
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
            ArmorPiercingShell shellEntity = new ArmorPiercingShell(tankEntity, world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
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
            HighExplosiveShell shellEntity = new HighExplosiveShell(tankEntity, world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
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
            HeatShell shellEntity = new HeatShell(tankEntity, world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
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
            APCRShell shellEntity = new APCRShell(tankEntity, world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
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
            LowCaliberShell shellEntity = new LowCaliberShell(tankEntity, world);
            float xRot = playerEntity.getXRot();
            xRot = Math.max(-10.0F, Math.min(10.0F, xRot));
            shellEntity.shootFromRotation(tankEntity, xRot, playerEntity.getYRot(), 0.0F, 3.5F, 0F);
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


    public static IGuiOverlay OVERLAY = ((gui, poseStack, partialTick, width, height) -> {
        Entity en = gui.getMinecraft().player.getVehicle();
        int x = width / 2;
        int y = height;


        if(!gui.getMinecraft().isWindowActive() || gui.getMinecraft().options.hideGui)
            return;
        Player player = gui.getMinecraft().player;
        if(player == null)
            return;

        Entity entity = player.getVehicle();
        if(!(entity instanceof BaseTankEntity))
            return;
        Level world = player.level();
        if (!world.isClientSide) {
            return;
        }



    });

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
    public boolean hurt(DamageSource pSource, float pAmount) {
        Entity entity = pSource.getDirectEntity();


        if (pSource.type() == level().damageSources().cactus().type() || pSource.type() == level().damageSources().sweetBerryBush().type()) {
            return false;
        }

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
        return super.hurt(pSource, pAmount);
    }
    public double getOverlaySpeed() {
        return blocksPerSecond;
    }

    @Override
    protected void removePassenger(Entity pPassenger) {
        this.setSpeed(0f);
        this.timeInVehicle = 0;
        super.removePassenger(pPassenger);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "shoot_controller", state -> PlayState.STOP)
                .triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE)));

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