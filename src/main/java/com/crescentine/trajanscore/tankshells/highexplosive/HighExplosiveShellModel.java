package com.crescentine.trajanscore.tankshells.highexplosive;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HighExplosiveShellModel extends GeoModel<HighExplosiveShell> {
    @Override
    public ResourceLocation getModelResource(HighExplosiveShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/he_shell.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(HighExplosiveShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/he_shell.png");
    }
    @Override
    public ResourceLocation getAnimationResource(HighExplosiveShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/he_shell.animation.json");
    }
}
