package com.crescentine.trajanscore.tankshells.smoke;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SmokeShellRenderer extends GeoEntityRenderer<SmokeShell> {
    public SmokeShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SmokeShellModel());
        this.shadowRadius = 0.2F;
    }
}