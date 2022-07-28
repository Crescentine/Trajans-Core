package com.crescentine.trajanscore.entity;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.entity.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.entity.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.entity.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.entity.tankshells.highexplosive.HighExplosiveShellEntity;
import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TrajansCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TrajansCoreEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TrajansCoreMod.MOD_ID);

    public static final RegistryObject<EntityType<StandardShellEntity>> STANDARD_SHELL = ENTITY_TYPES.register("standard_shell",
            () -> EntityType.Builder.<StandardShellEntity>of(StandardShellEntity::new, MobCategory.MISC).sized(0.5f, 0.3f)
                    .clientTrackingRange(8).updateInterval(10)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "standard_shell").toString()));

    public static final RegistryObject<EntityType<HighExplosiveShellEntity>> HIGH_EXPLOSIVE_SHELL = ENTITY_TYPES.register("high_explosive_shell",
            () -> EntityType.Builder.<HighExplosiveShellEntity>of(HighExplosiveShellEntity::new, MobCategory.MISC).sized(0.5f, 0.3f)
                    .clientTrackingRange(8).updateInterval(10)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "high_explosion_shell").toString()));

    public static final RegistryObject<EntityType<HeatShell>> HEAT_SHELL = ENTITY_TYPES.register("heat_shell",
            () -> EntityType.Builder.<HeatShell>of(HeatShell::new, MobCategory.MISC).sized(0.5f, 0.3f)
                    .clientTrackingRange(8).updateInterval(10)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "heat_shell").toString()));

    public static final RegistryObject<EntityType<ArmorPiercingShell>> ARMOR_PIERCING_SHELL = ENTITY_TYPES.register("armor_piercing_shell",
            () -> EntityType.Builder.<ArmorPiercingShell>of(ArmorPiercingShell::new, MobCategory.MISC).sized(0.5f, 0.3f)
                    .clientTrackingRange(8).updateInterval(10)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "armor_piercing").toString()));

    public static final RegistryObject<EntityType<APCRShell>> APCR_SHELL = ENTITY_TYPES.register("apcr_shell",
            () -> EntityType.Builder.<APCRShell>of(APCRShell::new, MobCategory.MISC).sized(0.5f, 0.3f)
                    .clientTrackingRange(8).updateInterval(10)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "apcr_shell").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}