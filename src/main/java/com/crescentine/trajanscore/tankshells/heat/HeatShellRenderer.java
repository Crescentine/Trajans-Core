package com.crescentine.trajanscore.tankshells.heat;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class HeatShellRenderer extends GeoProjectilesRenderer<HeatShell> {
    public HeatShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HeatShellModel());
        this.shadowRadius = 0.2F;
    }
}