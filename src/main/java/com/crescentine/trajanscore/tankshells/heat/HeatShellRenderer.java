package com.crescentine.trajanscore.tankshells.heat;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HeatShellRenderer extends GeoEntityRenderer<HeatShell> {
    public HeatShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HeatShellModel());
        this.shadowRadius = 0.2F;
    }
}