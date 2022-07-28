package com.crescentine.trajanscore.entity.tankshells.highexplosive;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class HighExplosiveShellEntity extends BaseShellEntity {
    public HighExplosiveShellEntity(EntityType<? extends Entity> entityType, Level world) {
        super(TrajansCoreEntityTypes.HIGH_EXPLOSIVE_SHELL.get(), world);
        this.makesFire = true;
        this.damage = TrajansCoreConfig.highExplosiveShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.highExplosiveShellExplosionRadius.get();
    }
}
