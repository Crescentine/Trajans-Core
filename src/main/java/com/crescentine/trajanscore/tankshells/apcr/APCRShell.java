package com.crescentine.trajanscore.tankshells.apcr;

import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.TrajansCoreEntities;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.base.BaseShell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class APCRShell extends BaseShell {
    public APCRShell(EntityType<APCRShell> entityType, Level world) {
        super(entityType, world);
        damage = TrajansCoreConfig.APCRShellDamage.get();
        explosionRadius = TrajansCoreConfig.APCRShellExplosionRadius.get();
        fire = false;
    }

    public APCRShell(EntityType<APCRShell> entityType, double x, double y, double z, Level world) {
        super(TrajansCoreEntities.APCR_SHELL.get(), x, y, z, world);
        damage = TrajansCoreConfig.APCRShellDamage.get();
        explosionRadius = TrajansCoreConfig.APCRShellExplosionRadius.get();
        fire = false;
    }

    public APCRShell(LivingEntity player, Level world) {
        super(TrajansCoreEntities.APCR_SHELL.get(), player, world);
        damage = TrajansCoreConfig.APCRShellDamage.get();
        explosionRadius = TrajansCoreConfig.APCRShellExplosionRadius.get();
        fire = false;
    }

    @Override
    protected Item getDefaultItem() {
        return TrajansCoreItems.APCR_SHELL.get();
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }
}