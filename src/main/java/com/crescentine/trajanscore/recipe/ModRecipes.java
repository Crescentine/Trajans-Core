package com.crescentine.trajanscore.recipe;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TrajansCoreMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TrajansCoreMod.MOD_ID);

    public static void init() {
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static final RegistryObject<RecipeSerializer<?>> CRAFTER_SERIALIZER = RECIPE_SERIALIZERS.register("crafter", TankCrafterRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<TankCrafterRecipe>> CRAFTER_RECIPE = RECIPE_TYPES.register("crafter", () -> RecipeType.simple(new ResourceLocation(TrajansCoreMod.MOD_ID, "crafter")));

    public static final RegistryObject<RecipeSerializer<?>> PLATING_PRESS_SERIALIZER = RECIPE_SERIALIZERS.register("plating_press", PlatingPressRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<PlatingPressRecipe>> PLATING_PRESS_RECIPE = RECIPE_TYPES.register("plating_press", () -> RecipeType.simple(new ResourceLocation(TrajansCoreMod.MOD_ID, "plating_press")));

    public static final RegistryObject<RecipeSerializer<?>> ENGINE_FABRICATOR_SERIALIZER = RECIPE_SERIALIZERS.register("engine_fabricator", EngineFabricatorRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<EngineFabricatorRecipe>> ENGINE_FABRICATOR_RECIPE = RECIPE_TYPES.register("engine_fabricator", () -> RecipeType.simple(new ResourceLocation(TrajansCoreMod.MOD_ID, "engine_fabricator")));

    public static final RegistryObject<RecipeSerializer<?>> STEEL_MANUFACTURER_SERIALIZER = RECIPE_SERIALIZERS.register("steel_manufacturer", SteelManufacturerRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<SteelManufacturerRecipe>> STEEL_MANUFACTURER_RECIPE = RECIPE_TYPES.register("steel_manufacturer", () -> RecipeType.simple(new ResourceLocation(TrajansCoreMod.MOD_ID, "steel_manufacturer")));

    public static final RegistryObject<RecipeSerializer<?>> TURRET_FACTORY_SERIALIZER = RECIPE_SERIALIZERS.register("turret_factory", TurretFactoryRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<TurretFactoryRecipe>> TURRET_FACTORY_RECIPE = RECIPE_TYPES.register("turret_factory", () -> RecipeType.simple(new ResourceLocation(TrajansCoreMod.MOD_ID, "turret_factory")));

}