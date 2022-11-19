package com.crescentine.trajanscore.example_tank;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ExampleTankRenderer extends GeoEntityRenderer<ExampleTankEntity> {
    public ExampleTankRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ExampleTankModel());
        this.shadowRadius = 0.7F;
    }

    @Override
    public void render(GeoModel model, ExampleTankEntity animatable, float partialTick, RenderType type, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.render(model, animatable, partialTick, type, poseStack, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        if (animatable.getFuelAmount() > 0 && model.getBone("Engine").isPresent() && animatable.isVehicle() && animatable.isMoving()) {
            animatable.getCommandSenderWorld().addParticle(ParticleTypes.LARGE_SMOKE,
                    model.getBone("Engine").get().getWorldPosition().x,
                    model.getBone("Engine").get().getWorldPosition().y + 0.8,
                    model.getBone("Engine").get().getWorldPosition().z,
                    (animatable.getRandom().nextGaussian() * 0.0003D), -animatable.getRandom().nextGaussian() * 0.0003D,
                    (animatable.getRandom().nextGaussian() * 0.0003D));
        }
    }
}