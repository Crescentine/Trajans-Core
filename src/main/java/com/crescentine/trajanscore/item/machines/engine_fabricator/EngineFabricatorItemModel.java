package com.crescentine.trajanscore.item.machines.engine_fabricator;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EngineFabricatorItemModel extends AnimatedGeoModel<EngineFabricatorItem> {
    @Override
    public ResourceLocation getAnimationFileLocation(EngineFabricatorItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/engine_fabricator.animation.json");
    }
    @Override
    public ResourceLocation getModelLocation(EngineFabricatorItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/engine_fabricator.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(EngineFabricatorItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/engine_fabricator.png");
    }

}