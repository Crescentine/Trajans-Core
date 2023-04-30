package com.crescentine.trajanscore.tankshells.standard;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StandardShellModel extends GeoModel<StandardShell> {
    @Override
    public ResourceLocation getModelResource(StandardShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/standard_shell.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(StandardShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/standard_shell.png");
    }
    @Override
    public ResourceLocation getAnimationResource(StandardShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/standard_shell.animation.json");
    }
}
