package com.crescentine.trajanscore.tankshells.armorpiercing;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ArmorPiercingShell extends BaseShell {
    public double antiTankDamage = TrajansCoreConfig.armorPiercingShellDamageToArmoredVehicles.get();
    public ArmorPiercingShell(EntityType<ArmorPiercingShell> entityType, Level world) {
        super(entityType, world);
        damage = TrajansCoreConfig.armorPiercingShellDamage.get();
        explosionRadius = TrajansCoreConfig.armorPiercingExplosionRadius.get();
        fire = false;
    }
    public ArmorPiercingShell(EntityType<ArmorPiercingShell> entityType, double x, double y, double z, Level world) {
        super(TrajansCoreEntities.ARMOR_PIERCING_SHELL.get(), x, y, z, world);
        damage = TrajansCoreConfig.armorPiercingShellDamage.get();
        explosionRadius = TrajansCoreConfig.armorPiercingExplosionRadius.get();
        fire = false;
    }

    public ArmorPiercingShell(LivingEntity player, Level world) {
        super(TrajansCoreEntities.ARMOR_PIERCING_SHELL.get(), player, world);
        damage = TrajansCoreConfig.armorPiercingShellDamage.get();
        explosionRadius = TrajansCoreConfig.armorPiercingExplosionRadius.get();
        fire = false;
    }
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) damage);
        if (entity instanceof BaseTankEntity tank && tank.armored) {
            entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) antiTankDamage);
        }
    }
    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.ARMOR_PIERCING_SHELL.get();
    }

}