package com.crescentine.trajanscore.item.machines.plating_press;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PlatingPressItemRenderer extends GeoItemRenderer<PlatingPressItem> {
    public PlatingPressItemRenderer() {
        super(new PlatingPressItemModel());
    }

}
