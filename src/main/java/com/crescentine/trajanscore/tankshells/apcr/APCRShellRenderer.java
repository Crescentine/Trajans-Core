package com.crescentine.trajanscore.tankshells.apcr;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class APCRShellRenderer extends GeoEntityRenderer<APCRShell> {
    public APCRShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new APCRShellModel());
        this.shadowRadius = 0.2F;
    }
}