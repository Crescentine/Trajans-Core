package com.crescentine.trajanscore.tankshells.armorpiercing;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ArmorPiercingShellRenderer extends GeoEntityRenderer<ArmorPiercingShell> {
    public ArmorPiercingShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ArmorPiercingShellModel());
        this.shadowRadius = 0.2F;
    }
}