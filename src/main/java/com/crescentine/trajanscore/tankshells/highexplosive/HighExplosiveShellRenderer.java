package com.crescentine.trajanscore.tankshells.highexplosive;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class HighExplosiveShellRenderer extends GeoProjectilesRenderer<HighExplosiveShell> {
    public HighExplosiveShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HighExplosiveShellModel());
        this.shadowRadius = 0.2F;
    }
}