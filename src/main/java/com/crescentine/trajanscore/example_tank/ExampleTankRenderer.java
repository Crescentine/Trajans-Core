package com.crescentine.trajanscore.example_tank;

import com.crescentine.trajanscore.TankShootEvent;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class ExampleTankRenderer extends GeoEntityRenderer<ExampleTankEntity> {
    public ExampleTankRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ExampleTankModel());
        this.shadowRadius = 0.7F;
    }

    @Override
    public void render(ExampleTankEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
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