package com.crescentine.trajanscore;

import com.crescentine.trajanscore.item.TrajansCoreItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PartsItemGroup extends CreativeModeTab {

    public static final PartsItemGroup TANK_MOD_PARTS = new PartsItemGroup(CreativeModeTab.TABS.length,
            "trajanstanks_parts");

    public PartsItemGroup(int index, String label) {
        super(index, label);
    }
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(TrajansCoreItems.TANK_CONTROLLER.get());
    }
}