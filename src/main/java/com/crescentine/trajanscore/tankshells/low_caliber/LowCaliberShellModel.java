package com.crescentine.trajanscore.tankshells.low_caliber;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LowCaliberShellModel extends GeoModel<LowCaliberShell> {
    @Override
    public ResourceLocation getModelResource(LowCaliberShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/low_caliber_shell.json");
    }

    @Override
    public ResourceLocation getTextureResource(LowCaliberShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/low_cal_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LowCaliberShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/low_cal.animation.json");
    }
}