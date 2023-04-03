package com.crescentine.trajanscore.block.shellworkbench;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.block.platingpress.PlatingPressBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShellWorkbenchModel extends GeoModel<ShellWorkbenchBlockEntity> {
    @Override
    public ResourceLocation getAnimationResource(ShellWorkbenchBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/shell_workbench.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(ShellWorkbenchBlockEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/fixed_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShellWorkbenchBlockEntity entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/shell_workbench.png");
    }

}