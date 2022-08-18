package com.crescentine.trajanscore.tankshells.heat;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class HeatShell extends BaseShell {
    public HeatShell(EntityType<HeatShell> entityType, Level world) {
        super(entityType, world);
        damage = TrajansCoreConfig.heatShellDamage.get();
        explosionRadius = TrajansCoreConfig.heatShellExplosionRadius.get();
        fire = true;
    }
    public HeatShell(EntityType<HeatShell> entityType, double x, double y, double z, Level world) {
        super(TrajansCoreEntities.HEAT_SHELL.get(), x, y, z, world);
        damage = TrajansCoreConfig.heatShellDamage.get();
        explosionRadius = TrajansCoreConfig.heatShellExplosionRadius.get();
        fire = true;
    }

    public HeatShell(LivingEntity player, Level world) {
        super(TrajansCoreEntities.HEAT_SHELL.get(), player, world);
        damage = TrajansCoreConfig.heatShellDamage.get();
        explosionRadius = TrajansCoreConfig.heatShellExplosionRadius.get();
        fire = true;
    }
    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.HEAT_SHELL.get();
    }

}