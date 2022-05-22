package com.crescentine.trajanscore.block.turretfactory;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TurretFactoryModel extends AnimatedGeoModel<TurretFactoryBlockEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(TurretFactoryBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/turret_factpry.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(TurretFactoryBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/turret_factory.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TurretFactoryBlockEntity entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/turret_factory.png");
    }

}