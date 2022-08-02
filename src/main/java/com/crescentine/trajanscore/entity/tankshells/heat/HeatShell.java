package com.crescentine.trajanscore.entity.tankshells.heat;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class HeatShell extends BaseShellEntity {
    public HeatShell(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
        this.damage = TrajansCoreConfig.heatShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.heatShellExplosionRadius.get();
        this.makesFire = true;
    }
    public HeatShell(LivingEntity player, Level world) {
        super(TrajansCoreEntityTypes.HEAT_SHELL.get(), player, world);
    }
    public HeatShell(double x, double y, double z, Level world) {
        super(TrajansCoreEntityTypes.HEAT_SHELL.get(), x, y, z, world);
    }
}
