package com.crescentine.trajanscore.item;

import com.crescentine.trajanscore.PartsItemGroup;
import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.block.crafter.CrafterBlock;
import com.crescentine.trajanscore.block.engine_fabricator.EngineFabricatorBlock;
import com.crescentine.trajanscore.block.platingpress.PlatingPressBlock;
import com.crescentine.trajanscore.block.steelmanufacturer.SteelManufacturerBlock;
import com.crescentine.trajanscore.block.turretfactory.TurretFactoryBlock;
import com.crescentine.trajanscore.item.machines.engine_fabricator.EngineFabricatorItem;
import com.crescentine.trajanscore.item.machines.plating_press.PlatingPressItem;
import com.crescentine.trajanscore.item.machines.steel_manufacturer.SteelManufacturerItem;
import com.crescentine.trajanscore.item.machines.turret_factory.TurretFactoryItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TankModItems {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TrajansCoreMod.MOD_ID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TrajansCoreMod.MOD_ID);

    //Block Items
    public static final RegistryObject<Item> CRAFTER_BLOCK_ITEM = ITEMS.register("crafter_block",
            () -> new BlockItem(TankModItems.CRAFTER_BLOCK.get(), new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> PLATE_PRESS_BLOCK_ITEM = ITEMS.register("plate_press_block_item",
            () -> new PlatingPressItem(TankModItems.PLATE_PRESS_BLOCK.get(), new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> TURRET_FACTORY_BLOCK_ITEM = ITEMS.register("turret_factory_block_item",
            () -> new TurretFactoryItem(TankModItems.TURRET_FACTORY.get(), new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> ENGINE_FABRICATOR_ITEM = ITEMS.register("engine_fabricator_item",
            () -> new EngineFabricatorItem(TankModItems.ENGINE_FABRICATOR.get(), new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> STEEL_MANUFACTURER_ITEM = ITEMS.register("steel_manufacturer_item",
            () -> new SteelManufacturerItem(TankModItems.STEEL_MANUFACTURER.get(), new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    //Blocks
    public static final RegistryObject<Block> CRAFTER_BLOCK = registerBlock("crafter_block",
        () -> new CrafterBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.0f)));
    public static final RegistryObject<Block> PLATE_PRESS_BLOCK = registerBlock("plate_press_block", PlatingPressBlock::new);
    public static final RegistryObject<Block> ENGINE_FABRICATOR = registerBlock("engine_fabricator",
            EngineFabricatorBlock::new);
    public static final RegistryObject<Block> STEEL_MANUFACTURER = registerBlock("steel_manufacturer",
            SteelManufacturerBlock::new);
    public static final RegistryObject<Block> TURRET_FACTORY = registerBlock("turret_factory",
            TurretFactoryBlock::new);


    //Parts for Panzer 2
    public static final RegistryObject<Item> LIGHT_TANK_TURRET = ITEMS.register("light_tank_turret", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> LIGHT_ENGINE = ITEMS.register("light_engine", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> LIGHT_TANK_PLATING = ITEMS.register("light_tank_plating", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> LIGHT_TANK_TRACKS = ITEMS.register("light_tank_tracks", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> PANZER_TWO_BLUEPRINT = ITEMS.register("panzer_two_blueprint", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> TANK_CONTROLLER = ITEMS.register("steering_crafting_item", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));

    //Parts for Tiger
    public static final RegistryObject<Item> HEAVY_TANK_TURRET = ITEMS.register("heavy_tank_turret", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> HEAVY_ENGINE = ITEMS.register("heavy_engine", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> HEAVY_TANK_PLATING = ITEMS.register("heavy_tank_plating", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> HEAVY_TANK_TRACKS = ITEMS.register("heavy_tank_tracks", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> TIGER_BLUEPRINT = ITEMS.register("tiger_blueprint", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));


    //Parts for T34
    public static final RegistryObject<Item> MEDIUM_TANK_TURRET = ITEMS.register("medium_tank_turret", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> MEDIUM_ENGINE = ITEMS.register("medium_engine", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> MEDIUM_TANK_PLATING = ITEMS.register("medium_tank_plating", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> MEDIUM_TANK_TRACMS = ITEMS.register("medium_tank_tracks", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> T34_BLUEPRINT = ITEMS.register("t34_blueprint", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));

    public static final RegistryObject<Item> CRUISERMK1_BLUEPRINT = ITEMS.register("cruisermk1_blueprint", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));
    public static final RegistryObject<Item> M4SHERMAN_BLUEPRINT = ITEMS.register("m4sherman_blueprint", () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS)));


    //Machine Parts
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer",
            () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS).durability(35)));
    public static final RegistryObject<Item> BOLSTER_PLATE = ITEMS.register("bolster_plate",
            () -> new Item(new Item.Properties().tab(PartsItemGroup.TANK_MOD_PARTS).durability(20)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }
}
