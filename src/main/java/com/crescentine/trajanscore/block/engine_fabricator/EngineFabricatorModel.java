package com.crescentine.trajanscore.block.engine_fabricator;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EngineFabricatorModel extends AnimatedGeoModel<EngineFabricatorBlockEntity> {
    @Override
    public ResourceLocation getAnimationResource(EngineFabricatorBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/engine_fabricator.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(EngineFabricatorBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/engine_fabricator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EngineFabricatorBlockEntity entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/engine_fabricator.png");
    }

}