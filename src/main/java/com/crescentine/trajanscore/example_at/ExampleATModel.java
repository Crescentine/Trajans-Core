package com.crescentine.trajanscore.example_at;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.example_tank.ExampleTankEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class ExampleATModel extends GeoModel<ExampleATEntity> {
    public ResourceLocation getModelResource(ExampleATEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/artillery.geo.json");
    }
    public ResourceLocation getTextureResource(ExampleATEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/artillery.png");
    }
    public ResourceLocation getAnimationResource(ExampleATEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/artillery.json");
    }


    @Override
    public void setCustomAnimations(ExampleATEntity animatable, long instanceId, AnimationState<ExampleATEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        CoreGeoBone gun = this.getAnimationProcessor().getBone("Barrel");
        CoreGeoBone main = this.getAnimationProcessor().getBone("Artillery");
        gun.setRotY(0f);
        main.setRotY(0f);

        if (animatable.hasControllingPassenger()) {
            Entity rider = animatable.getControllingPassenger();
            if (animatable.isVehicle() && rider instanceof Player player && player.level().isClientSide() && animatable.hasControllingPassenger()) {
                float elevationAngle = rider.getXRot();
                main.setRotY((float) Math.toRadians(-animatable.getYRot()));

                float maxElevation = 8;
                float minElevation = -3;

                if (elevationAngle > maxElevation) {
                    elevationAngle = maxElevation;
                } else if (elevationAngle < minElevation) {
                    elevationAngle = minElevation;
                }

                float targetGunRotZ = (float) Math.toRadians(elevationAngle);

                float lerpFactor = 1;

                gun.setRotX(-targetGunRotZ);


            } else {
                main.setRotY((float) Math.toRadians(-animatable.getYRot()));
            }
        }
    }
}