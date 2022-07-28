package com.crescentine.trajanscore.entity.tankshells.heat;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class HeatShell extends BaseShellEntity {
    public HeatShell(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
        this.damage = TrajansCoreConfig.heatShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.heatShellExplosionRadius.get();
        this.makesFire = true;
    }
}
