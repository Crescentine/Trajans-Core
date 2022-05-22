package com.crescentine.trajanscore.block.engine_fabricator;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EngineFabricatorRenderer extends GeoBlockRenderer<EngineFabricatorBlockEntity> {
    public EngineFabricatorRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new EngineFabricatorModel());
    }

}