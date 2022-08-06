package com.crescentine.trajanscore.integration.jei_compat;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.recipe.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

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
        registration.addRecipeCatalyst(new ItemStack(TrajansCoreItems.STEEL_MANUFACTURER.get()), SteelManufacturerRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TrajansCoreItems.ENGINE_FABRICATOR.get()), EngineFabricatorRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TrajansCoreItems.PLATE_PRESS_BLOCK.get()), PlatingPressRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TrajansCoreItems.CRAFTER_BLOCK.get()), TankCrafterRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TrajansCoreItems.TURRET_FACTORY.get()), TurretFactoryCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TrajansCoreItems.SHELL_WORKBENCH.get()), ShellWorkbenchRecipeCategory.RECIPE_TYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new EngineFabricatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new TankCrafterRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new PlatingPressRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new TurretFactoryCategory(registration.getJeiHelpers().getGuiHelper()),
                new ShellWorkbenchRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
                new SteelManufacturerRecipeCategory(registration.getJeiHelpers().getGuiHelper())

        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<TankCrafterRecipe> tankCrafterRecipes = rm.getAllRecipesFor(ModRecipes.CRAFTER_RECIPE.get());
        List<PlatingPressRecipe> platingPressRecipes = rm.getAllRecipesFor(ModRecipes.PLATING_PRESS_RECIPE.get());
        List<EngineFabricatorRecipe> engineFabricatorRecipes = rm.getAllRecipesFor(ModRecipes.ENGINE_FABRICATOR_RECIPE.get());
        List<SteelManufacturerRecipe> manufacturerRecipes = rm.getAllRecipesFor(ModRecipes.STEEL_MANUFACTURER_RECIPE.get());
        List<TurretFactoryRecipe> turretFactoryRecipes = rm.getAllRecipesFor(ModRecipes.TURRET_FACTORY_RECIPE.get());
        List<ShellWorkbenchRecipe> shellWorkbenchRecipes = rm.getAllRecipesFor(ModRecipes.SHELL_WORKBENCH.get());

        registration.addRecipes(TurretFactoryCategory.RECIPE_TYPE, turretFactoryRecipes);
        registration.addRecipes(EngineFabricatorRecipeCategory.RECIPE_TYPE, engineFabricatorRecipes);
        registration.addRecipes(PlatingPressRecipeCategory.RECIPE_TYPE, platingPressRecipes);
        registration.addRecipes(SteelManufacturerRecipeCategory.RECIPE_TYPE, manufacturerRecipes);
        registration.addRecipes(TankCrafterRecipeCategory.RECIPE_TYPE, tankCrafterRecipes);
        registration.addRecipes(ShellWorkbenchRecipeCategory.RECIPE_TYPE, shellWorkbenchRecipes);
    }
}