package com.crescentine.trajanscore.block.shellworkbench;

import com.crescentine.trajanscore.block.engine_fabricator.EngineFabricatorBlockEntity;
import com.crescentine.trajanscore.block.engine_fabricator.EngineFabricatorModel;
import com.crescentine.trajanscore.block.platingpress.PlatingPressBlockEntity;
import com.crescentine.trajanscore.block.platingpress.PlatingPressModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ShellWorkbenchRenderer extends GeoBlockRenderer<ShellWorkbenchBlockEntity> {
    public ShellWorkbenchRenderer() {
        super(new ShellWorkbenchModel());
    }
}