package com.crescentine.trajanscore.block;


import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.block.crafter.CrafterBlockEntity;
import com.crescentine.trajanscore.block.engine_fabricator.EngineFabricatorBlockEntity;
import com.crescentine.trajanscore.block.platingpress.PlatingPressBlockEntity;
import com.crescentine.trajanscore.block.shellworkbench.ShellWorkbenchBlockEntity;
import com.crescentine.trajanscore.block.steelmanufacturer.SteelManufacturerBlockEntity;
import com.crescentine.trajanscore.block.turretfactory.TurretFactoryBlockEntity;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TankModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, TrajansCoreMod.MOD_ID);

    public static RegistryObject<BlockEntityType<CrafterBlockEntity>> CRAFTER =
            BLOCK_ENTITIES.register("crafter", () -> BlockEntityType.Builder.of
                    (CrafterBlockEntity::new, TrajansCoreItems.CRAFTER_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<PlatingPressBlockEntity>> PLATING_PRESS =
            BLOCK_ENTITIES.register("plating_press", () -> BlockEntityType.Builder.of
                    (PlatingPressBlockEntity::new, TrajansCoreItems.PLATE_PRESS_BLOCK.get()).build(null));
    public static RegistryObject<BlockEntityType<EngineFabricatorBlockEntity>> ENGINE_FABRICATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("engine_fabricator", () -> BlockEntityType.Builder.of
                    (EngineFabricatorBlockEntity::new, TrajansCoreItems.ENGINE_FABRICATOR.get()).build(null));
    public static RegistryObject<BlockEntityType<SteelManufacturerBlockEntity>> STEEL_MANUFACTURER =
            BLOCK_ENTITIES.register("steel_manufacturer", () -> BlockEntityType.Builder.of
                    (SteelManufacturerBlockEntity::new, TrajansCoreItems.STEEL_MANUFACTURER.get()).build(null));
    public static RegistryObject<BlockEntityType<TurretFactoryBlockEntity>> TURRET_FACTORY =
            BLOCK_ENTITIES.register("turret_factory", () -> BlockEntityType.Builder.of
                    (TurretFactoryBlockEntity::new, TrajansCoreItems.TURRET_FACTORY.get()).build(null));

    public static RegistryObject<BlockEntityType<ShellWorkbenchBlockEntity>> SHELL_WORKBENCH =
            BLOCK_ENTITIES.register("shell_workbench", () -> BlockEntityType.Builder.of
                    (ShellWorkbenchBlockEntity::new, TrajansCoreItems.SHELL_WORKBENCH_BLOCK.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
