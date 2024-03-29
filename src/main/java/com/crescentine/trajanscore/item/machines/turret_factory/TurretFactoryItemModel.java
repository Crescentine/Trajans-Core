package com.crescentine.trajanscore.item.machines.turret_factory;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TurretFactoryItemModel extends GeoModel<TurretFactoryItem> {
    @Override
    public ResourceLocation getAnimationResource(TurretFactoryItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/turret)factory.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(TurretFactoryItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/turret_factory.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TurretFactoryItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/turret_factory.png");
    }

}