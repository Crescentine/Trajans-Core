package com.crescentine.trajanscore.entity.tankshells.standard;

import com.crescentine.trajanscore.config.TrajansCoreConfig;
import com.crescentine.trajanscore.entity.TrajansCoreEntityTypes;
import com.crescentine.trajanscore.entity.tankshells.BaseShellEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class StandardShellEntity extends BaseShellEntity implements IAnimatable {
    public StandardShellEntity(EntityType<? extends Entity> entityType, Level world) {
        super(TrajansCoreEntityTypes.STANDARD_SHELL.get(), world);
        this.makesFire = false;
        this.damage = TrajansCoreConfig.standardShellDamage.get();
        this.explosionRadius = TrajansCoreConfig.standardShellExplosionRadius.get();
    }
}
