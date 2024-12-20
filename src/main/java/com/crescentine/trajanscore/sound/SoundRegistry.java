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

    public static RegistryObject<SoundEvent> TANK_IDLE = SOUNDS.register("tank_idle",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TrajansCoreMod.MOD_ID, "tank_idle")));

    public static RegistryObject<SoundEvent> TANK_MOVE = SOUNDS.register("tank_move",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TrajansCoreMod.MOD_ID, "tank_move")));

}