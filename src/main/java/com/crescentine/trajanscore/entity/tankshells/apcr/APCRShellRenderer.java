package com.crescentine.trajanscore.entity.tankshells.apcr;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class APCRShellRenderer extends GeoProjectilesRenderer<APCRShell> {
    public APCRShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new APCRShellModel());
        this.shadowRadius = 0.2F;
    }
}