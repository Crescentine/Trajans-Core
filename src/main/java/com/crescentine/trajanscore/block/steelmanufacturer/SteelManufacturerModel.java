package com.crescentine.trajanscore.block.steelmanufacturer;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SteelManufacturerModel extends AnimatedGeoModel<SteelManufacturerBlockEntity> {
    @Override
    public ResourceLocation getAnimationResource(SteelManufacturerBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/steel_manufacturer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(SteelManufacturerBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/steel_manufacturer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SteelManufacturerBlockEntity entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/steel_manufacturer.png");
    }

}