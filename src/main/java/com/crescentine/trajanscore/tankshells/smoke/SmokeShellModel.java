package com.crescentine.trajanscore.tankshells.smoke;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SmokeShellModel extends GeoModel<SmokeShell> {
    @Override
    public ResourceLocation getModelResource(SmokeShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/he_shell.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(SmokeShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/he_shell.png");
    }
    @Override
    public ResourceLocation getAnimationResource(SmokeShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/he_shell.animation.json");
    }
}
