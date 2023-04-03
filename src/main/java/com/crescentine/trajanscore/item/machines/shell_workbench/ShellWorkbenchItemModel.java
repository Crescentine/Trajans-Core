package com.crescentine.trajanscore.item.machines.shell_workbench;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.block.shellworkbench.ShellWorkbenchBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShellWorkbenchItemModel extends GeoModel<ShellWorkbenchItem> {
    @Override
    public ResourceLocation getAnimationResource(ShellWorkbenchItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/shell_workbench.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(ShellWorkbenchItem animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/shell_workbench.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShellWorkbenchItem entity) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/block/shell_workbench.png");
    }
}

