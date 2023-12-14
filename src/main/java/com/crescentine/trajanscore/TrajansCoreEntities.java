package com.crescentine.trajanscore;

import com.crescentine.trajanscore.example_at.ExampleATEntity;
import com.crescentine.trajanscore.example_tank.ExampleTankEntity;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShell;
import com.crescentine.trajanscore.tankshells.low_caliber.LowCaliberShell;
import com.crescentine.trajanscore.tankshells.low_caliber.LowCaliberShellModel;
import com.crescentine.trajanscore.tankshells.standard.StandardShell;
import com.crescentine.trajanscore.temp_luchs_test.LuchsTankEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(modid = TrajansCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TrajansCoreEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TrajansCoreMod.MOD_ID);

    public static final RegistryObject<EntityType<StandardShell>> STANDARD_SHELL = ENTITY_TYPES.register("standard_shell",
            () -> EntityType.Builder.<StandardShell>of(StandardShell::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(8).updateInterval(3)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "heat_shell").toString()));

    public static final RegistryObject<EntityType<HighExplosiveShell>> HIGH_EXPLOSIVE_SHELL = ENTITY_TYPES.register("high_explosive_shell",
            () -> EntityType.Builder.<HighExplosiveShell>of(HighExplosiveShell::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(8).updateInterval(3)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "heat_shell").toString()));

    public static final RegistryObject<EntityType<HeatShell>> HEAT_SHELL = ENTITY_TYPES.register("heat_shell",
            () -> EntityType.Builder.<HeatShell>of(HeatShell::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(8).updateInterval(3)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "heat_shell").toString()));

    public static final RegistryObject<EntityType<ArmorPiercingShell>> ARMOR_PIERCING_SHELL = ENTITY_TYPES.register("armor_piercing_shell",
            () -> EntityType.Builder.<ArmorPiercingShell>of(ArmorPiercingShell::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(8).updateInterval(3)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "armor_piercing_shell").toString()));

    public static final RegistryObject<EntityType<APCRShell>> APCR_SHELL = ENTITY_TYPES.register("apcr_shell",
            () -> EntityType.Builder.<APCRShell>of(APCRShell::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(8).updateInterval(3)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "apcr_shell").toString()));


    public static final RegistryObject<EntityType<LowCaliberShell>> LOW_CALIBER_SHELL = ENTITY_TYPES.register("low_caliber_shell",
            () -> EntityType.Builder.<LowCaliberShell>of(LowCaliberShell::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .clientTrackingRange(8).updateInterval(3)
                    .build(new ResourceLocation(TrajansCoreMod.MOD_ID, "apcr_shell").toString()));



    public static final RegistryObject<EntityType<ExampleTankEntity>> EXAMPLE_TANK = ENTITY_TYPES.register("example_tank",
            () -> EntityType.Builder.<ExampleTankEntity>of(ExampleTankEntity::new, MobCategory.MISC).sized(4.0F, 2.3F)
                    .clientTrackingRange(10).build("example_tank"));



    public static final RegistryObject<EntityType<LuchsTankEntity>> LUCHS = ENTITY_TYPES.register("luchs",
            () -> EntityType.Builder.<LuchsTankEntity>of(LuchsTankEntity::new, MobCategory.MISC).sized(3.5F, 2.3F)
                    .clientTrackingRange(10).build("luchs"));

    /*public static final RegistryObject<EntityType<ExampleATEntity>> EXAMPLE_AT = ENTITY_TYPES.register("example_at",
            () -> EntityType.Builder.<ExampleATEntity>of(ExampleATEntity::new, MobCategory.MISC).sized(1.65f, 1.05f)
                    .clientTrackingRange(10).build("example_at"));*/

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    @SubscribeEvent
    public static void entityAttributesInit(EntityAttributeCreationEvent event) {
        /*event.put(EXAMPLE_TANK.get(), ExampleTankEntity.createAttributes().build());
        event.put(LUCHS.get(), LuchsTankEntity.createAttributes().build());*/
        //event.put(EXAMPLE_AT.get(), ExampleATEntity.createAttributes().build());
    }
}
