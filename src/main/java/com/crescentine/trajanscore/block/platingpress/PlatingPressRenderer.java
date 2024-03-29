package com.crescentine.trajanscore.block.platingpress;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PlatingPressRenderer extends GeoBlockRenderer<PlatingPressBlockEntity> {
    public PlatingPressRenderer() {
        super(new PlatingPressModel());
    }
    
}