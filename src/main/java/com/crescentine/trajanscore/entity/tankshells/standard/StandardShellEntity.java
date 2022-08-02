package com.crescentine.trajanscore.entity.tankshells.standard;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import com.crescentine.trajanscore.item.TankModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class StandardShellEntity extends BaseShellEntity implements IAnimatable {
    public StandardShellEntity(EntityType<StandardShellEntity> entityType, Level world) {
        super(entityType, world);
        this.makesFire = false;
        this.damage = TrajansCoreConfig.standardShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.standardShellExplosionRadius.get();
    }
    public StandardShellEntity(LivingEntity player, Level world) {
        super(TrajansCoreEntityTypes.STANDARD_SHELL.get(), player, world);
    }
    public StandardShellEntity(double x, double y, double z, Level world) {
        super(TrajansCoreEntityTypes.STANDARD_SHELL.get(), x, y, z, world);
    }
}
