package com.crescentine.trajanscore.block.turretfactory;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class TurretFactoryRenderer extends GeoBlockRenderer<TurretFactoryBlockEntity> {
    public TurretFactoryRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new TurretFactoryModel());
    }

}