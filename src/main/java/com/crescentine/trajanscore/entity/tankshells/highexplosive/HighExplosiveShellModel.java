package com.crescentine.trajanscore.entity.tankshells.highexplosive;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HighExplosiveShellModel extends AnimatedGeoModel<HighExplosiveShellEntity> {
    @Override
    public ResourceLocation getModelResource(HighExplosiveShellEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/he_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HighExplosiveShellEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/he_shell.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HighExplosiveShellEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/he_shell.animation.json");
    }
}
