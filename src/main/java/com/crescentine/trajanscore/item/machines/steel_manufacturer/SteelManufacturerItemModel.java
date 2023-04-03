package com.crescentine.trajanscore.item.machines.steel_manufacturer;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SteelManufacturerItemModel extends GeoModel<SteelManufacturerItem> {
    @Override
    public ResourceLocation getAnimationResource(SteelManufacturerItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/steel_manufacturer.animation.json");
    }
    @Override
    public ResourceLocation getModelResource(SteelManufacturerItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/steel_manufacturer.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(SteelManufacturerItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/steel_manufacturer.png");
    }

}