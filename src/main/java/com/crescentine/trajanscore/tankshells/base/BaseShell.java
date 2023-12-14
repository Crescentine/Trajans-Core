package com.crescentine.trajanscore.tankshells.base;

import com.crescentine.trajanscore.item.TrajansCoreItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BaseShell extends ThrowableItemProjectile implements GeoEntity {
    public double damage;
    public int explosionRadius;
    public boolean fire;
    private boolean inGround;
    private float rot;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public BaseShell(EntityType<?> entityType, Level world) {
        super((EntityType<? extends ThrowableItemProjectile>) entityType, world);
        this.updateHeading();


        this.setNoGravity(true);
    }
    public BaseShell(EntityType<?> entityType, double x, double y, double z, Level world) {
        super((EntityType<? extends ThrowableItemProjectile>) entityType, x, y, z, world);

    }
    public BaseShell(EntityType<?> entityType, LivingEntity player, Level world) {
        super((EntityType<? extends ThrowableItemProjectile>) entityType, player, world);
        this.rot = player.yHeadRot;
    }

    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.STANDARD_SHELL.get();
    }
    @Override
    protected float getGravity() {
        return 0.05f;
    }

    private boolean shouldFall() {
        return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
    }
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.playSound(SoundEvents.GENERIC_EXPLODE, 2F, 1F);
        if (entity instanceof LivingEntity) {
            entity.hurt(damageSources().thrown(this, this.getOwner()), (float) damage);
            entity.playSound(SoundEvents.GENERIC_EXPLODE, 2F, 1F);
        }
    }

    public void updateHeading()
    {
        double horizontalDistance = this.getDeltaMovement().horizontalDistance();
        this.setYRot((float) (Mth.atan2(this.getDeltaMovement().x(), this.getDeltaMovement().z()) * (180D / Math.PI)));
        this.setXRot((float) (Mth.atan2(this.getDeltaMovement().y(), horizontalDistance) * (180D / Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }
    @Override
    protected void onHit(HitResult p_70227_1_){
        super.onHit(p_70227_1_);
        if (!this.level().isClientSide) { // checks if the world is client
            this.level().broadcastEntityEvent(this, (byte) 3); // particle?
            if (!level().isClientSide) {
                this.level().explode(this, getX(), getY(), getZ(), explosionRadius, fire, Level.ExplosionInteraction.BLOCK);
                this.kill();
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.updateHeading();
        if (this.level().isClientSide) {
            for (int i = 5; i > 0; i--) {
                this.level().addParticle(ParticleTypes.CLOUD, false, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
            }
            if (this.level().random.nextInt(2) == 0) {
                this.level().addParticle(ParticleTypes.SMOKE, true, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                this.level().addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}