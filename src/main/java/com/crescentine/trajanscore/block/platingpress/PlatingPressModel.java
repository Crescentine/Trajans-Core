package com.crescentine.trajanscore.block.platingpress;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PlatingPressModel extends GeoModel<PlatingPressBlockEntity> {
    @Override
    public ResourceLocation getAnimationResource(PlatingPressBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/plating_press.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PlatingPressBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/plating_press.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlatingPressBlockEntity entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/plating_press.png");
    }

}