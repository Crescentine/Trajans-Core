package com.crescentine.trajanscore;

import com.crescentine.trajanscore.item.TrajansCoreItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ShellsItemGroup extends CreativeModeTab {

    public static final ShellsItemGroup SHELLS_ITEM_GROUP = new ShellsItemGroup(CreativeModeTab.TABS.length,
            "trajanscore_shells");

    public ShellsItemGroup(int index, String label) {
        super(index, label);
    }
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get());
    }
}