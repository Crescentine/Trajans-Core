package com.crescentine.trajanscore.tankshells.standard;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StandardShellRenderer extends GeoEntityRenderer<StandardShell> {
    public StandardShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new StandardShellModel());
        this.shadowRadius = 0.2F;
    }
}