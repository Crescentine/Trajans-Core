package com.crescentine.trajanscore.block.shellworkbench;

import com.crescentine.trajanscore.block.platingpress.PlatingPressBlockEntity;
import com.crescentine.trajanscore.block.platingpress.PlatingPressModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ShellWorkbenchRenderer extends GeoBlockRenderer<ShellWorkbenchBlockEntity> {
    public ShellWorkbenchRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ShellWorkbenchModel());
    }

}