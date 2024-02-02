package com.crescentine.trajanscore.temp_luchs_test;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.model.GeoModel;

public class LuchsTankModel extends GeoModel<LuchsTankEntity> {
    public ResourceLocation getModelResource(LuchsTankEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/luchs.geo.json");
    }

    public ResourceLocation getTextureResource(LuchsTankEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/luchs.png");
    }

    public ResourceLocation getAnimationResource(LuchsTankEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/luchs_animation.json");
    }

    @Override
    public void setCustomAnimations(LuchsTankEntity animatable, long instanceId, software.bernie.geckolib.core.animation.AnimationState<LuchsTankEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        CoreGeoBone turret = this.getAnimationProcessor().getBone("turret");
/*        CoreGeoBone gun = this.getAnimationProcessor().getBone("TopPart").getChildBones().get(1);
        CoreGeoBone manlet = this.getAnimationProcessor().getBone("TopPart").getChildBones().get(0);*/

        turret.setRotY(0);
        if (animatable.hasControllingPassenger()) {
            Entity rider = animatable.getControllingPassenger();
            if (animatable.isVehicle() && rider instanceof Player player && player.level().isClientSide() && animatable.hasControllingPassenger()) {
                turret.setRotY((float) -Math.toRadians(rider.getYHeadRot() - animatable.getYRot()));
                /*float elevationAngle = rider.getXRot();

                float maxElevation = 5;
                float minElevation = -3;

                if (elevationAngle > maxElevation) {
                    elevationAngle = maxElevation;
                } else if (elevationAngle < minElevation) {
                    elevationAngle = minElevation;
                }

                float targetGunRotZ = (float) Math.toRadians(elevationAngle);
                float targetManletRotZ = (float) Math.toRadians(elevationAngle);

                float lerpFactor = 1;

                gun.setRotZ(targetGunRotZ);
                manlet.setRotZ(targetManletRotZ);*/


            }
        }
    }

}