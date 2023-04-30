package com.crescentine.trajanscore.tankshells.highexplosive;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HighExplosiveShellRenderer extends GeoEntityRenderer<HighExplosiveShell> {
    public HighExplosiveShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HighExplosiveShellModel());
        this.shadowRadius = 0.2F;
    }
}