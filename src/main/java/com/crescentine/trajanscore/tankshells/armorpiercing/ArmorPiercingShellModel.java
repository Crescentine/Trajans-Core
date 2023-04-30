package com.crescentine.trajanscore.tankshells.armorpiercing;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ArmorPiercingShellModel extends GeoModel<ArmorPiercingShell> {
    @Override
    public ResourceLocation getModelResource(ArmorPiercingShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "geo/armor_piercing.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArmorPiercingShell object) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/item/armor_piercing.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArmorPiercingShell animatable) {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "animations/armor_piercing.animation.json");
    }
}