package com.crescentine.trajanscore.entity.tankshells;

import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

@SuppressWarnings("EntityConstructor")
public class BaseShellEntity extends ThrowableItemProjectile implements IAnimatable {
    public int explosionRadius;
    public double damage;
    public boolean makesFire;
    private final AnimationFactory factory = new AnimationFactory(this);
    public BaseShellEntity(EntityType<? extends Entity> entityType, Level world) {
        super((EntityType<? extends ThrowableItemProjectile>) entityType, world);
    }
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onHit(HitResult p_70227_1_) {
        super.onHit(p_70227_1_);
        if (!this.level.isClientSide) { // checks if the world is client
            this.level.broadcastEntityEvent(this, (byte) 3); // particle?
            if (!level.isClientSide) {
                this.level.explode(this, getX(), getY(), getZ(), explosionRadius, makesFire, Explosion.BlockInteraction.BREAK);
                this.kill();
            }
        }
    }
    @Override
    protected float getGravity() {
        return 0.026f;
    }
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.playSound(SoundEvents.GENERIC_EXPLODE, 2F, 1F);
        if (entity instanceof LivingEntity) {
            entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) (damage * 1.0f));
            entity.playSound(SoundEvents.GENERIC_EXPLODE, 2F, 1F);

        }
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
