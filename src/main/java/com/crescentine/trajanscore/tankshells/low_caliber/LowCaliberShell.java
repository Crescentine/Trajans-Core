package com.crescentine.trajanscore.tankshells.low_caliber;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class LowCaliberShell extends BaseShell {
    public LowCaliberShell(EntityType<LowCaliberShell> entityType, Level world) {
        super(entityType, world);
        damage = TrajansCoreConfig.APCRShellDamage.get();
        explosionRadius = TrajansCoreConfig.APCRShellExplosionRadius.get();
        fire = false;
    }

    public LowCaliberShell(EntityType<LowCaliberShell> entityType, double x, double y, double z, Level world) {
        super(TrajansCoreEntities.LOW_CALIBER_SHELL.get(), x, y, z, world);
        damage = TrajansCoreConfig.LowCaliberShellDamage.get();
        explosionRadius = TrajansCoreConfig.LowCaliberShellExplosionRadius.get();
        fire = false;
    }

    public LowCaliberShell(LivingEntity player, Level world) {
        super(TrajansCoreEntities.LOW_CALIBER_SHELL.get(), player, world);
        damage = TrajansCoreConfig.LowCaliberShellDamage.get();
        explosionRadius = TrajansCoreConfig.LowCaliberShellExplosionRadius.get();
        fire = false;
    }



    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.LOW_CALIBER_SHELL.get();
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }
}