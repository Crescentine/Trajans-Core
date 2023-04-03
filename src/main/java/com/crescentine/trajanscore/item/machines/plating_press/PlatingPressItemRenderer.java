package com.crescentine.trajanscore.item.machines.plating_press;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoItemRenderer;


public class PlatingPressItemRenderer extends GeoItemRenderer<PlatingPressItem> {
    public PlatingPressItemRenderer() {
        super(new PlatingPressItemModel());
    }

}
