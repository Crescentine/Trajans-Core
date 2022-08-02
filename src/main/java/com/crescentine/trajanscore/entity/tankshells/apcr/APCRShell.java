package com.crescentine.trajanscore.entity.tankshells.apcr;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class APCRShell extends BaseShellEntity {
    public APCRShell(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
        this.damage = TrajansCoreConfig.APCRShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.APCRShellExplosionRadius.get();
        this.makesFire = false;
    }
    public APCRShell(LivingEntity player, Level world) {
        super(TrajansCoreEntityTypes.APCR_SHELL.get(), player, world);
    }
    public APCRShell(double x, double y, double z, Level world) {
        super(TrajansCoreEntityTypes.APCR_SHELL.get(), x, y, z, world);
    }
}
