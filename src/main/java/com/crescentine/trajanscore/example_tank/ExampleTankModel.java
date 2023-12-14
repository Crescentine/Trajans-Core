package com.crescentine.trajanscore.example_tank;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animatable.model.CoreGeoModel;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.model.GeoModel;

import static java.lang.Math.atan2;

public class ExampleTankModel extends GeoModel<ExampleTankEntity> {
    public ResourceLocation getModelResource(ExampleTankEntity object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/tank.geo.json");
    }

    public ResourceLocation getTextureResource(ExampleTankEntity object) {
        if (object.isCamoRed) {
            return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/red.png");

        }
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/tank.png");
    }

    public ResourceLocation getAnimationResource(ExampleTankEntity animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/tank.json");
    }

   @Override
    public void setCustomAnimations(ExampleTankEntity animatable, long instanceId, software.bernie.geckolib.core.animation.AnimationState<ExampleTankEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        CoreGeoBone main = this.getAnimationProcessor().getBone("Tank");
       CoreGeoBone rwl = this.getAnimationProcessor().getBone("RightWheel");
       CoreGeoBone lwl = this.getAnimationProcessor().getBone("LeftWheel");


        /*CoreGeoBone turret = this.getAnimationProcessor().getBone("TopPart");
        CoreGeoBone gun = this.getAnimationProcessor().getBone("TopPart").getChildBones().get(1);
        CoreGeoBone manlet = this.getAnimationProcessor().getBone("TopPart").getChildBones().get(0);

        turret.setRotY(0);*/
        if (animatable.hasControllingPassenger()) {
            Entity rider = animatable.getControllingPassenger();
            if (animatable.isVehicle() && rider instanceof Player player && player.level().isClientSide() && animatable.hasControllingPassenger()) {
                /*turret.setRotY((float) -Math.toRadians(rider.getYHeadRot() - animatable.getYRot()));
                float elevationAngle = rider.getXRot();

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
                manlet.setRotZ(targetManletRotZ);
                main.setRotY((float) Math.toRadians(animatable.getYRot()));
                rwl.setRotY((float) -Math.toRadians(animatable.getYRot()));
                lwl.setRotY((float) -Math.toRadians(animatable.getYRot()));
    */


            }
        }
    }


}