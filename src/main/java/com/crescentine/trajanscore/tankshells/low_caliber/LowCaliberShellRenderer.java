package com.crescentine.trajanscore.tankshells.low_caliber;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LowCaliberShellRenderer extends GeoEntityRenderer<LowCaliberShell> {
    public LowCaliberShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new LowCaliberShellModel());
        this.shadowRadius = 0.2F;
    }
}