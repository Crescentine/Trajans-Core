package com.crescentine.trajanscore.entity.tankshells.standard;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StandardShellModel extends AnimatedGeoModel<StandardShellEntity> {
    @Override
    public ResourceLocation getModelResource(StandardShellEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/standard_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StandardShellEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/standard_shell.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StandardShellEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/standard_shell.animation.json");
    }
}
