package com.crescentine.trajanscore.item.machines.turret_factory;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TurretFactoryItemModel extends AnimatedGeoModel<TurretFactoryItem> {
    @Override
    public ResourceLocation getAnimationFileLocation(TurretFactoryItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/turret)factory.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(TurretFactoryItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/turret_factory.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TurretFactoryItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/turret_factory.png");
    }

}