package com.crescentine.trajanscore.integration.jei_compat;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.item.TankModItems;
import com.crescentine.trajanscore.recipe.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class TankModJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TrajansCoreMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(TankModItems.STEEL_MANUFACTURER.get()), SteelManufacturerRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TankModItems.ENGINE_FABRICATOR.get()), EngineFabricatorRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TankModItems.PLATE_PRESS_BLOCK.get()), PlatingPressRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TankModItems.CRAFTER_BLOCK.get()), TankCrafterRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TankModItems.TURRET_FACTORY.get()), TurretFactoryCategory.RECIPE_TYPE);


    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new EngineFabricatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new TankCrafterRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new PlatingPressRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new TurretFactoryCategory(registration.getJeiHelpers().getGuiHelper()),
                new SteelManufacturerRecipeCategory(registration.getJeiHelpers().getGuiHelper())

        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<TankCrafterRecipe> tankCrafterRecipes = rm.getAllRecipesFor(TankCrafterRecipe.Type.INSTANCE);
        List<PlatingPressRecipe> platingPressRecipes = rm.getAllRecipesFor(PlatingPressRecipe.Type.INSTANCE);
        List<EngineFabricatorRecipe> engineFabricatorRecipes = rm.getAllRecipesFor(EngineFabricatorRecipe.Type.INSTANCE);
        List<SteelManufacturerRecipe> manufacturerRecipes = rm.getAllRecipesFor(SteelManufacturerRecipe.Type.INSTANCE);
        List<TurretFactoryRecipe> turretFactoryRecipes = rm.getAllRecipesFor(TurretFactoryRecipe.Type.INSTANCE);

        registration.addRecipes(TurretFactoryCategory.RECIPE_TYPE, turretFactoryRecipes);
        registration.addRecipes(EngineFabricatorRecipeCategory.RECIPE_TYPE, engineFabricatorRecipes);
        registration.addRecipes(PlatingPressRecipeCategory.RECIPE_TYPE, platingPressRecipes);
        registration.addRecipes(SteelManufacturerRecipeCategory.RECIPE_TYPE, manufacturerRecipes);
        registration.addRecipes(TankCrafterRecipeCategory.RECIPE_TYPE, tankCrafterRecipes);
    }
}