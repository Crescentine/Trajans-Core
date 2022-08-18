package com.crescentine.trajanscore.example_tank;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ExampleTankRenderer extends GeoEntityRenderer<ExampleTankEntity> {
    public ExampleTankRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ExampleTankModel());
        this.shadowRadius = 0.7F;
    }
}