package com.crescentine.trajanscore.sound;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            TrajansCoreMod.MOD_ID);

    public static RegistryObject<SoundEvent> AT_SHOOT = SOUNDS.register("at_shoot",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TrajansCoreMod.MOD_ID, "at_shoot")));
}