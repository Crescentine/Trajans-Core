package com.crescentine.trajanscore.example_at;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.example_tank.ExampleTankEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ExampleATModel extends AnimatedGeoModel<ExampleATEntity> {
    public ResourceLocation getModelResource(ExampleATEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/artillery.geo.json");
    }
    public ResourceLocation getTextureResource(ExampleATEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/artillery.png");
    }
    public ResourceLocation getAnimationResource(ExampleATEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/blank.json");
    }
}