package com.crescentine.trajanscore.block.turretfactory;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TurretFactoryModel extends GeoModel<TurretFactoryBlockEntity> {
    @Override
    public ResourceLocation getAnimationResource(TurretFactoryBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/turret_factpry.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(TurretFactoryBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/turret_factory.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TurretFactoryBlockEntity entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/turret_factory.png");
    }

}