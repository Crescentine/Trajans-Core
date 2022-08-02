package com.crescentine.trajanscore.entity.tankshells.armorpiercing;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellEntity;
import com.crescentine.trajanscore.entity.vehicle.TankVehicle;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ArmorPiercingShell extends BaseShellEntity {
    public double antiTankDamage;
    public ArmorPiercingShell(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
        this.antiTankDamage = TrajansCoreConfig.armorPiercingShellDamageToArmoredVehicles.get();
        this.damage = TrajansCoreConfig.armorPiercingShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.armorPiercingExplosionRadius.get();
        this.makesFire = false;
    }
    public ArmorPiercingShell(LivingEntity player, Level world) {
        super(TrajansCoreEntityTypes.ARMOR_PIERCING_SHELL.get(), player, world);
    }
    public ArmorPiercingShell(double x, double y, double z, Level world) {
        super(TrajansCoreEntityTypes.ARMOR_PIERCING_SHELL.get(), x, y, z, world);
    }
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) damage);
        if (entity instanceof TankVehicle) {
            entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) damage);
        }
    }
}
