package com.crescentine.trajanscore.tankshells.highexplosive;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class HighExplosiveShell extends BaseShell {
    public HighExplosiveShell(EntityType<HighExplosiveShell> entityType, Level world) {
        super(entityType, world);
        damage = TrajansCoreConfig.highExplosiveShellDamage.get();
        explosionRadius = TrajansCoreConfig.highExplosiveShellExplosionRadius.get();
        fire = false;
    }
    public HighExplosiveShell(EntityType<HighExplosiveShell> entityType, double x, double y, double z, Level world) {
        super(TrajansCoreEntities.HIGH_EXPLOSIVE_SHELL.get(), x, y, z, world);
        damage = TrajansCoreConfig.highExplosiveShellDamage.get();
        explosionRadius = TrajansCoreConfig.highExplosiveShellExplosionRadius.get();
        fire = false;
    }

    public HighExplosiveShell(LivingEntity player, Level world) {
        super(TrajansCoreEntities.HIGH_EXPLOSIVE_SHELL.get(), player, world);
        damage = TrajansCoreConfig.highExplosiveShellDamage.get();
        explosionRadius = TrajansCoreConfig.highExplosiveShellExplosionRadius.get();
        fire = false;
    }
    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.HEAT_SHELL.get();
    }

}