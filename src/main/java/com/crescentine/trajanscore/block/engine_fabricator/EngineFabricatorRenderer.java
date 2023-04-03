package com.crescentine.trajanscore.block.engine_fabricator;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class EngineFabricatorRenderer extends GeoBlockRenderer<EngineFabricatorBlockEntity> {
    public EngineFabricatorRenderer() {
        super(new EngineFabricatorModel());
    }

}