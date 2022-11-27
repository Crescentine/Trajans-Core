package com.crescentine.trajanscore.example_at;

import com.crescentine.trajanscore.example_tank.ExampleTankEntity;
import com.crescentine.trajanscore.example_tank.ExampleTankModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ExampleATRenderer extends GeoEntityRenderer<ExampleATEntity> {
    public ExampleATRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ExampleATModel());
        this.shadowRadius = 0.7F;
    }
}