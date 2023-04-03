package com.crescentine.trajanscore.block.steelmanufacturer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SteelManufacturerRenderer extends GeoBlockRenderer<SteelManufacturerBlockEntity> {
    public SteelManufacturerRenderer() {
        super(new SteelManufacturerModel());
    }

}