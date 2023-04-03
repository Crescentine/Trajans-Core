package com.crescentine.trajanscore.item.machines.engine_fabricator;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EngineFabricatorItemModel extends GeoModel<EngineFabricatorItem> {
    @Override
    public ResourceLocation getAnimationResource(EngineFabricatorItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/engine_fabricator.animation.json");
    }
    @Override
    public ResourceLocation getModelResource(EngineFabricatorItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/engine_fabricator.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(EngineFabricatorItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/engine_fabricator.png");
    }

}