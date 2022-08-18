package com.crescentine.trajanscore.tankshells.standard;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;

public class StandardShell extends BaseShell {
    public StandardShell(EntityType<StandardShell> entityType, Level world) {
        super(entityType, world);
        damage = TrajansCoreConfig.standardShellDamage.get();
        explosionRadius = TrajansCoreConfig.standardShellExplosionRadius.get();
        fire = false;
    }
    public StandardShell(EntityType<?> entityType, double x, double y, double z, Level world) {
        super(TrajansCoreEntities.STANDARD_SHELL.get(), x, y, z, world);
        damage = TrajansCoreConfig.standardShellDamage.get();
        explosionRadius = TrajansCoreConfig.standardShellExplosionRadius.get();
        fire = false;
    }

    public StandardShell(LivingEntity player, Level world) {
        super(TrajansCoreEntities.STANDARD_SHELL.get(), player, world);
        damage = TrajansCoreConfig.standardShellDamage.get();
        explosionRadius = TrajansCoreConfig.standardShellExplosionRadius.get();
        fire = false;
    }

    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.STANDARD_SHELL.get();
    }

}
