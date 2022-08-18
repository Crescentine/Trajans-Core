package com.crescentine.trajanscore.tankshells.apcr;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class APCRShellModel extends AnimatedGeoModel<APCRShell> {
    @Override
    public ResourceLocation getModelResource(APCRShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/apcr_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(APCRShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/apcr_shell.png");
    }

    @Override
    public ResourceLocation getAnimationResource(APCRShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/apcr_shell.animation.json");
    }
}