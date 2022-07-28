package com.crescentine.trajanscore;

import com.crescentine.trajanscore.item.TankModItems;
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
        return new ItemStack(TankModItems.HIGH_EXPLOSIVE_SHELL.get());
    }
}