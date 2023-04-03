package com.crescentine.trajanscore.block.turretfactory;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TurretFactoryRenderer extends GeoBlockRenderer<TurretFactoryBlockEntity> {
    public TurretFactoryRenderer() {
        super(new TurretFactoryModel());
    }

}