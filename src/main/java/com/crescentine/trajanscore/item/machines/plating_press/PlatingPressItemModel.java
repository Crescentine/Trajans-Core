package com.crescentine.trajanscore.item.machines.plating_press;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PlatingPressItemModel extends AnimatedGeoModel<PlatingPressItem> {
    @Override
    public ResourceLocation getAnimationResource(PlatingPressItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/plating_press.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(PlatingPressItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/plating_press.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlatingPressItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/plating_press.png");
    }

}