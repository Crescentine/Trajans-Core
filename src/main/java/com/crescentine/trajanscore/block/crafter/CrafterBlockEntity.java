package com.crescentine.trajanscore.block.crafter;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.block.InventoryBlockEntity;
import com.crescentine.trajanscore.block.TankModBlockEntities;
import com.crescentine.trajanscore.recipe.ModRecipes;
import com.crescentine.trajanscore.recipe.TankCrafterRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrafterBlockEntity extends InventoryBlockEntity implements MenuProvider {
    public static final Component TITLE = Component.translatable(
            "container." + TrajansCoreMod.MOD_ID + ".crafter");

    public CrafterBlockEntity(BlockPos pos, BlockState state) {
        super(TankModBlockEntities.CRAFTER.get(), pos, state, 9);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tank Crafter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory p_39955_, Player p_39956_) {
        return null;
    }

    public static void tick(Level world, CrafterBlockEntity entity) {
        if (hasRecipe(entity)) {
            craftItem(entity);
        }
    }


    private static boolean hasRecipe(CrafterBlockEntity entity) {
        Level world = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            inventory.setItem(i, entity.getItemInSlot(i));
        }

        Optional<TankCrafterRecipe> match = world.getRecipeManager()
                .getRecipeFor(ModRecipes.CRAFTER_RECIPE.get(), inventory, world);

        return match.isPresent()
                && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResult());

    }


    private static void craftItem(CrafterBlockEntity entity) {
        Level world = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i = 0; i < entity.inventory.getSlots(); i++) {
            inventory.setItem(i, entity.inventory.getStackInSlot(i));
        }

        Optional<TankCrafterRecipe> match = world.getRecipeManager()
                .getRecipeFor(ModRecipes.CRAFTER_RECIPE.get(), inventory, world);

        if (match.isPresent()) {
            entity.extractItem(0);
            entity.extractItem(1);
            entity.extractItem(2);
            entity.extractItem(3);
            entity.extractItem(4);
            entity.extractItem(5);
            entity.extractItem(6);
            entity.insertItem(8, new ItemStack(match.get().getResult().getItem(),
                    1));
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(8).getItem() == output.getItem() || inventory.getItem(8).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(8).getMaxStackSize() > inventory.getItem(8).getCount();
    }

}
