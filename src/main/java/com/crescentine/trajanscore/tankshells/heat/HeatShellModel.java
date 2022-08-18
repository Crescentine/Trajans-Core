package com.crescentine.trajanscore.tankshells.heat;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HeatShellModel extends AnimatedGeoModel<HeatShell> {
    @Override
    public ResourceLocation getModelResource(HeatShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/heat_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HeatShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/heat_shell.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HeatShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/heat_shell.animation.json");
    }
}